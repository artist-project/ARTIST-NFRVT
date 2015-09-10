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
 * Martin Fleck, TUWIEN: Modified to static calls
 * 
 * Initially developed in the context of ARTIST EU project www.artist-project.eu
 */
package eu.artist.postmigration.nfrvt.strategy.benchmark;

import eu.artist.postmigration.nfrvt.lang.util.MigrationResourceSet;
import eu.artist.postmigration.nfrvt.lang.util.builder.MeasurementModelBuilder;
import eu.artist.postmigration.nfrvt.resources.MigrationLibraryResourcesUtil;
import eu.artist.postmigration.nfrvt.resources.constants.ARTIST_SimpleTypes;
import eu.artist.postmigration.nfrvt.resources.constants.Java_PrimitiveTypes;
import eu.artist.postmigration.nfrvt.resources.constants.MARTE_LibraryConstants;
import eu.artist.postmigration.nfrvt.strategy.benchmark.BenchmarkStrategy;
import eu.artist.postmigration.nfrvt.strategy.benchmark.BenchmarkConstants.WP7_MeasurementType;
import eu.artist.postmigration.nfrvt.strategy.benchmark.BenchmarkConstants.Workload;

public class TestQueries {

	public static void main(String[] args) throws Exception {
		MigrationResourceSet set = MigrationLibraryResourcesUtil.createMigrationResourceSet(true);
		MigrationLibraryResourcesUtil.init(set);
		set.loadModel(MARTE_LibraryConstants.LIBRARY_URI);
		set.loadModel(Java_PrimitiveTypes.PACKAGE_URI);
		MeasurementModelBuilder builder = new MeasurementModelBuilder(set);
		
		BenchmarkStrategy.appendAvailabilityMeasurement(builder, ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.AMAZON_EC2, "AvailabilityObservation");
		BenchmarkStrategy.appendBenchmarkMeasurement(builder, 
				WP7_MeasurementType.responseTime, Workload.ALL_Y, ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.AMAZON_EC2, "ResponseTimeObservation");
		BenchmarkStrategy.appendBenchmarkMeasurement(builder, 
				WP7_MeasurementType.throughput, Workload.ALL_Y, ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.AMAZON_EC2, "ThroughputObservationY");

		builder.save("output/test.measurement");
	}
}