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
 * Martin Fleck, TUWIEN: slight adaptations, renames
 * 
 * Initially developed in the context of ARTIST EU project www.artist-project.eu
 */
package eu.artist.postmigration.nfrvt.strategy.benchmark;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import eu.artist.postmigration.nfrvt.lang.nsl.nsl.QuantitativeProperty;
import eu.artist.postmigration.nfrvt.lang.util.builder.MeasurementModelBuilder.MeasurementType;
import eu.artist.postmigration.nfrvt.lang.util.builder.MeasurementModelBuilder.ObservationLevel;
import eu.artist.postmigration.nfrvt.resources.constants.ARTIST_NFPCatalogue;
import eu.artist.postmigration.nfrvt.resources.constants.ARTIST_SimpleTypes;
import eu.artist.postmigration.nfrvt.resources.constants.MARTE_BasicNFPTypes;

/**
 * @author aliki
 */
public class BenchmarkConstants {
    
	protected static final String DEFAULT_PROPERTIES_FILE = "DBconfig.properties";
	
	private static Properties DEFAULT_PROPERTIES = new Properties();
	static {
		DEFAULT_PROPERTIES.put("SQLdatabaseIP", "147.102.19.124");
		DEFAULT_PROPERTIES.put("SQLDBuser", "root");
		DEFAULT_PROPERTIES.put("SQLDBKey", "root");
		DEFAULT_PROPERTIES.put("3alibIP", "147.102.19.194");
	}
	
	protected static Properties properties;
	
	public static Properties getProperties() {
		if(BenchmarkConstants.properties != null)
			return BenchmarkConstants.properties;
		
		Properties properties = new Properties(DEFAULT_PROPERTIES);
		try {
			URL url = new URL("platform:/plugin/eu.artist.postmigration.nfrvt.strategy.benchmark/DBconfig.properties");
			properties.load(url.openStream());
		} catch(Exception e) {
			; // use defaults
		}
		return properties;
	}
	
	public static void setProperties(Properties properties) {
		BenchmarkConstants.properties = new Properties(DEFAULT_PROPERTIES);
		for(Entry<Object, Object> entry : properties.entrySet())
			if(entry.getValue() != null)
				BenchmarkConstants.properties.setProperty(entry.getKey().toString(), entry.getValue().toString());
	}
	
	// wp7measurement(imports for measurement, ObservationLevel, measurementtype, NFPCatalogue property)
	public enum WP7_MeasurementType{
		
		responseTime(new EObject[]{MARTE_BasicNFPTypes.Element.PACKAGE, ARTIST_SimpleTypes.Element.PACKAGE},
				ObservationLevel.Code, MeasurementType.Measured, ARTIST_NFPCatalogue.Element.AVERAGE_RESPONSE_TIME),
		throughput(new EObject[]{MARTE_BasicNFPTypes.Element.PACKAGE, ARTIST_SimpleTypes.Element.PACKAGE}, 
				ObservationLevel.Model, MeasurementType.Measured, ARTIST_NFPCatalogue.Element.THROUGHPUT),
		availability(new EObject[]{MARTE_BasicNFPTypes.Element.PACKAGE, ARTIST_SimpleTypes.Element.PACKAGE}, 
				ObservationLevel.Model, MeasurementType.Calculated, ARTIST_NFPCatalogue.Element.DOWN_TIME_IN_PERCENT);	

		private final EObject[] importNamespace;
		private final ObservationLevel observationLevel;
		private final MeasurementType measurementType;
		private final QuantitativeProperty property;
			
		private WP7_MeasurementType(EObject[] importNamespace, ObservationLevel observationLevel, MeasurementType measurementType, QuantitativeProperty property){
			this.importNamespace = importNamespace;
			this.observationLevel = observationLevel;
			this.measurementType = measurementType;
			this.property = property;
		}
			
		public EObject[] getImportNamespace() {
			return importNamespace;
		}

		public ObservationLevel getObservationLevel() {
			return observationLevel;
		}

		public MeasurementType getMeasurementType() {
			return measurementType;
		}

		public QuantitativeProperty getProperty() {
			return property;
		}
			
	}
	
	// providers for Availability (Must be mapped to Provider enum)
	public enum AvailabilityProvider {
		aws(60,ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.AMAZON_EC2.getName(),"amazontest"),
		google(300, ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.GOOGLE_APP_ENGINE.getName(), "datastore"),
		microsofAzure(60, ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.MICROSOFT_AZURE.getName(), "azureblob");
		
		private final int quantumOfTime;
		private final String abbreviation;
		private final String databaseName;
		
		public static AvailabilityProvider get(String abbreviation){
			for (AvailabilityProvider p : AvailabilityProvider.values()){
				if (abbreviation.equals(p.getAbbreviation())) return p;
			}
			return null;	
		}
		private AvailabilityProvider(int quantumOfTime, String availabilityAbbreviation, String databaseName){
			this.databaseName = databaseName;
			this.quantumOfTime = quantumOfTime;
			this.abbreviation = availabilityAbbreviation;
		}
		
		public String getDatabaseName(){
			return databaseName;
		}
		public String getAbbreviation(){
			return abbreviation;
		}
		
		public int getQuantumOfTime(){
			return quantumOfTime;
		}
	}
	
	//
	public enum BenchmarkProvider {
		amazon(ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.AMAZON_EC2.getName()), // AmazonEC2
		google(ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.GOOGLE_APP_ENGINE.getName()), // GoogleAppEngine
		azure(ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.MICROSOFT_AZURE.getName()), // MicrosoftAzure
		flexiant("FLEXIANT");

		private final String abbreviation;
		
		private BenchmarkProvider(String abbreviation){
			this.abbreviation = abbreviation;
		}
		
		private static final Map<String, BenchmarkProvider> lookup = new HashMap<String, BenchmarkProvider>();
	        static {
	            for (BenchmarkProvider d : BenchmarkProvider.values())
	                lookup.put(d.getAbbreviation(), d);
	    }

	    public String getAbbreviation() {
	    	return abbreviation;
	    }

	    public static BenchmarkProvider get(String abbreviation) {
	    	return lookup.get(abbreviation);
	    }
	}
	
	// DatabaseNFP in database (different for each benchmark), DatabaseNFP in NFPCatalogue
    public enum DatabaseNFP {
    	READ_Averagelatency_ms(ARTIST_NFPCatalogue.FQN.AVERAGE_RESPONSE_TIME, new Benchmark[]{Benchmark.YCSB}),
    	INSERT_Averagelatency_ms(ARTIST_NFPCatalogue.FQN.AVERAGE_RESPONSE_TIME, new Benchmark[]{Benchmark.YCSB}),
    	SCAN_Averagelatency_ms(ARTIST_NFPCatalogue.FQN.AVERAGE_RESPONSE_TIME, new Benchmark[]{Benchmark.YCSB}),
    	UPDATE_Averagelatency_ms(ARTIST_NFPCatalogue.FQN.AVERAGE_RESPONSE_TIME, new Benchmark[]{Benchmark.YCSB}),
    	Latency(ARTIST_NFPCatalogue.FQN.AVERAGE_RESPONSE_TIME, new Benchmark[]{Benchmark.Filebench}),
    	Avg_lat(ARTIST_NFPCatalogue.FQN.AVERAGE_RESPONSE_TIME, new Benchmark[]{Benchmark.DataCaching}),
        Throughput_ops_sec(ARTIST_NFPCatalogue.FQN.THROUGHPUT, new Benchmark[]{Benchmark.YCSB}), // eu.artist.property.catalogue.nonfunctional.Throughput
        OpsPerSecond(ARTIST_NFPCatalogue.FQN.THROUGHPUT, new Benchmark[]{Benchmark.Filebench}),
        Downtime(ARTIST_NFPCatalogue.FQN.DOWN_TIME_IN_PERCENT, new Benchmark[]{Benchmark.ThreeALib})
        ;
        
        private final String abbreviation;
        private final Benchmark[] benchmark;
        
        private static final Map<String, List<Map<Benchmark,DatabaseNFP>>> lookup = new HashMap<String, List<Map<Benchmark, DatabaseNFP>>>();
        static {
            for (DatabaseNFP d : DatabaseNFP.values()){
            	Map <Benchmark, DatabaseNFP> map = new HashMap<Benchmark, DatabaseNFP>();
        		for (Benchmark a : d.getBenchmarks()){
        			map.put(a, d);
        		}
        		if(lookup.containsKey(d.getAbbreviation())){
                	List<Map<Benchmark,DatabaseNFP>> list = lookup.get(d.getAbbreviation());
                	list.add(map);
                	lookup.put(d.getAbbreviation(), list);
            	}
            	else{
            		List<Map<Benchmark,DatabaseNFP>> list = new ArrayList<Map<Benchmark,DatabaseNFP>>();
            		list.add(map);
            		lookup.put(d.getAbbreviation(),list);
            	}
            }
        }

        private DatabaseNFP(String abbreviation, Benchmark[] benchmark) {
            this.abbreviation = abbreviation;
            this.benchmark = benchmark;
        }
       
        public String getAbbreviation() {
            return abbreviation;
        }
        
        public Benchmark[] getBenchmarks(){
        	return benchmark;
        }
        
        public static List<DatabaseNFP> get(String abbreviation, Benchmark benchmark){
        	List<Map<Benchmark,DatabaseNFP>> list = lookup.get(abbreviation);
        	Iterator<Map<Benchmark,DatabaseNFP>> it = list.iterator();
        	List<DatabaseNFP> nfps = new ArrayList<DatabaseNFP>();
        	while(it.hasNext()){
        		Map <Benchmark, DatabaseNFP> map = it.next();
        		if (map.containsKey(benchmark))
        			nfps.add(map.get(benchmark));
        	}
        	return nfps;
        }
    }
    
    // All different sets of benchmarks
    public enum Benchmark{
    	YCSB, 
    	DaCapo, 
    	Filebench, 
    	ThreeALib, 
    	DataCaching;
    }
    
    // Workload corresponding to each benchmark
    public enum Workload {
    	workloada(Benchmark.YCSB),
    	workloadb(Benchmark.YCSB),
    	workloadc(Benchmark.YCSB),
    	workloadd(Benchmark.YCSB),
    	workloade(Benchmark.YCSB),
    	
    	avrora(Benchmark.DaCapo),
    	eclipse(Benchmark.DaCapo),
    	fop(Benchmark.DaCapo),
    	h2(Benchmark.DaCapo),
    	jython(Benchmark.DaCapo),
    	pmd(Benchmark.DaCapo),
    	tomcat(Benchmark.DaCapo),
    	xalan(Benchmark.DaCapo),
    	
    	fileserver(Benchmark.Filebench),
    	varmail(Benchmark.Filebench),
    	videoserver(Benchmark.Filebench),
    	webproxy(Benchmark.Filebench),
    	webserver(Benchmark.Filebench),
    	
    	ALL_Y(Benchmark.YCSB),
    	ALL_D(Benchmark.DaCapo),
    	ALL_F(Benchmark.Filebench),
    	ALL_DC(Benchmark.DataCaching)
    	;
    	
    	private final Benchmark benchmark;
    	
    	private static final Map<Benchmark, Workload> lookup = new HashMap<Benchmark, Workload>();
    	static {
    		for (Workload d : Workload.values())
    			lookup.put(d.getBenchmark(), d);
    		}
    	
    	private Workload(Benchmark benchmark) {
			this.benchmark = benchmark;
		}
    	
    	public Benchmark getBenchmark() {
            return benchmark;
        }
    	
    	public String workloadName() {
    		if (!this.name().contains("ALL_"))
    				return this.name();
    		else return "ALL";
    	}

        public static Workload get(Benchmark benchmark) {
            return lookup.get(benchmark);
        }
    }

}
