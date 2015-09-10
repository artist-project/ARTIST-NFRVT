/*******************************************************************************
 * Copyright (c) 2015 ICCS
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Aliki Copaneli, ICCS: Initial API
 * Martin Fleck, TUWIEN: Rename, updates class to allow static calls and append 
 *                       measurements
 * 
 * Initially developed in the context of ARTIST EU project www.artist-project.eu
 */
package eu.artist.postmigration.nfrvt.strategy.benchmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.eclipse.uml2.uml.EnumerationLiteral;

import com.mongodb.MongoInternalException;

import eu.artist.postmigration.nfrvt.lang.pml.pml.Observation;
import eu.artist.postmigration.nfrvt.lang.util.MigrationResourceUtil;
import eu.artist.postmigration.nfrvt.lang.util.builder.MeasurementModelBuilder;
import eu.artist.postmigration.nfrvt.strategy.benchmark.BenchmarkConstants.Benchmark;
import eu.artist.postmigration.nfrvt.strategy.benchmark.BenchmarkConstants.DatabaseNFP;
import eu.artist.postmigration.nfrvt.strategy.benchmark.BenchmarkConstants.WP7_MeasurementType;
import eu.artist.postmigration.nfrvt.strategy.benchmark.BenchmarkConstants.Workload;

/**
 * @author aliki: Initial API
 * @author Martin Fleck: Updates class to allow static calls
 */
public class BenchmarkStrategy {
	
	public static String PROPERTY_FILE = BenchmarkConstants.DEFAULT_PROPERTIES_FILE;
	
	public static void setProperties(Properties properties) {
		BenchmarkConstants.setProperties(properties);
	}
	
	public static boolean isDBAvailable() {
		return AvailabilityCalculator.isDBAvailable();
	}
	
	protected static void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			; // fail silently 
		}
	}

	public static MeasurementModelBuilder appendAvailabilityMeasurement(MeasurementModelBuilder builder, EnumerationLiteral provider, String observationName) {
		WP7_MeasurementType measurementType = BenchmarkConstants.WP7_MeasurementType.availability;

		BenchmarkConstants.AvailabilityProvider availabilityProvider = 
				BenchmarkConstants.AvailabilityProvider.get(provider.getName());

		int quantumOfTime = availabilityProvider.getQuantumOfTime();

		// Calulate availability
		MultipleDCResults responseAvailability = null;
		MultipleDCResults responseCloudSleuthAvailability = null;
		try {
			responseAvailability = AvailabilityCalculator.calculateAvailability(quantumOfTime, 2014, 11, 1, 2014, 11, 30);
		} catch(MongoInternalException e) {
			System.err.println("Could not calculate availability. No connection to database?");
		}
		
		try {
			responseCloudSleuthAvailability = AvailabilityCalculator.calculateCloudSleuthAvailability(2014, 11, 1, 2014, 11, 30);
		} catch(MongoInternalException e) {
			System.err.println("Could not calculate availability. No connection to database?");
		}

		if(responseAvailability == null || responseCloudSleuthAvailability == null)
			return builder;

//		for(EObject namespace : measurementType.getImportNamespace()) 
//			builder.addImportNamespace(namespace);

		// Builder add observations and benchmarks
		for (int i = 0; i < responseAvailability.DC.size(); i++) {
			Observation observation = builder.addObservation(
					observationName,
					measurementType.getObservationLevel(),
					measurementType.getMeasurementType());
			
			builder.addBenchmark(
					observation,
					builder.createMeasurementName(observation),
					measurementType.getProperty(),
					provider,
					responseCloudSleuthAvailability.DC.get(i),
					null,
					null,
					builder.createValue(responseCloudSleuthAvailability.availability.get(i)), 
					"genericAvailability"
					);
			
			sleep(100);

			builder.addBenchmark(
					observation, 
					builder.createMeasurementName(observation),
					measurementType.getProperty(), 
					provider, 
					responseAvailability.DC.get(i),
					null,
					null,
					builder.createValue(responseAvailability.availability.get(i)), 
					"SLAAvailability");

		}
		return builder;
	}

	public static MeasurementModelBuilder appendBenchmarkMeasurement(
			MeasurementModelBuilder builder, WP7_MeasurementType measurementType, 
			Workload workload, EnumerationLiteral provider, String observationName) throws Exception{
		
		Benchmark benchmark = workload == null ? null : workload.getBenchmark();
		
		// initializations
		List<DatabaseNFP> properties = BenchmarkConstants.DatabaseNFP.get(
				MigrationResourceUtil.getFullyQualifiedName(measurementType.getProperty()), 
				benchmark);
		
		Map<String, Map<String, Double>> finalResults = new HashMap<>();
		Map<String, Double> results = new HashMap<>();
		String providerName = BenchmarkConstants.BenchmarkProvider.get(provider.getName()).name();
		
		// database queries
		for(DatabaseNFP property : properties) {
			String propertyName = property.name();
			try {
				DynamicQueryExecutor executor = new DynamicQueryExecutor(providerName, property.name(), benchmark.name(), workload.workloadName());
				results = executor.setupAndExecuteQuery();
				finalResults.put(property.name(), results);
				System.out.println("Result for DatabaseNFP '" + propertyName + "': " + results.toString());
			} catch(NullPointerException nex){
				System.out.println("Skip result for: " + propertyName);
			}
		}
		
		// average if more than one columns in benchmarks database
		Map<String, Double> average = new HashMap<String, Double>(); // one row for each instanceType
		for(Map<String, Double> allResForOneMetric: finalResults.values()) {
			if(allResForOneMetric == null)
				continue;
			// for one metric
			for (Map.Entry<String, Double> entry : allResForOneMetric.entrySet()) {
				double value = entry.getValue();
				if (average.containsKey(entry.getKey())) {
					Double currentSum = average.get(entry.getKey());
					currentSum = currentSum + value;
					average.put(entry.getKey(), currentSum);
				}
				else {
					average.put(entry.getKey(), value);
				}
			}	
		}
		
		for(Map.Entry<String, Double> entry : average.entrySet()) 
			average.put(entry.getKey(), entry.getValue() / finalResults.size());
		
		// append results
//		for(EObject namespace : measurementType.getImportNamespace())
//			builder.addImportNamespace(namespace);
		
		Observation observation = builder.addObservation(
				observationName,
				measurementType.getObservationLevel(), 
				measurementType.getMeasurementType());
		
		//store results to modelbuilder
		for(Entry<String, Map<String, Double>> result : finalResults.entrySet()) {
			if(result == null || result.getValue() == null)
				continue;
			for (Map.Entry<String, Double> entry : result.getValue().entrySet()){
				String info = result.getKey();
				builder.addBenchmark(
						observation,
						builder.createMeasurementName(observation),
						measurementType.getProperty(),
						provider,
						entry.getKey(),
						benchmark.name(),
						workload.workloadName(),
						builder.createValue(entry.getValue()),
						info);
				sleep(100);
			}
		}
		
		if(finalResults.size() > 1)
			for(Map.Entry<String, Double> entry : average.entrySet()) {
				String info = "average";
				builder.addBenchmark(
						observation, 
						builder.createMeasurementName(observation),
						measurementType.getProperty(),
						provider,
						entry.getKey(),
						benchmark.name(),
						workload.workloadName(),
						builder.createValue(entry.getValue()),
						info);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		return builder;
	}
}
