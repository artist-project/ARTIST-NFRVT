package eu.artist.postmigration.nfrvt.strategy.staticanalysis;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.Model;

import eu.artist.migration.cmm.metricexplorer.UMLExplorer;
import eu.artist.postmigration.nfrvt.lang.pml.pml.Observation;
import eu.artist.postmigration.nfrvt.lang.util.MigrationResourceSet;
import eu.artist.postmigration.nfrvt.lang.util.builder.MeasurementModelBuilder;
import eu.artist.postmigration.nfrvt.lang.util.builder.MeasurementModelBuilder.MeasurementType;
import eu.artist.postmigration.nfrvt.lang.util.builder.MeasurementModelBuilder.ObservationLevel;
import eu.artist.postmigration.nfrvt.resources.constants.ARTIST_NFPCatalogue;
import eu.artist.postmigration.nfrvt.resources.constants.ARTIST_SimpleTypes;
import eu.artist.postmigration.nfrvt.resources.constants.ARTIST_StaticPropertyCatalogue;

public class StaticAnalysis {
	private MigrationResourceSet resourceSet;
	
	public StaticAnalysis(MigrationResourceSet resourceSet) {
		this.resourceSet = resourceSet;		
	}
	
	public StaticAnalysis() {
		this(new MigrationResourceSet());
	}
	
	public MigrationResourceSet getResourceSet() {
		return resourceSet;
	}
	
	public MeasurementModelBuilder measure(Model umlModel, MeasurementModelBuilder builder) {
		if(umlModel == null)
			throw new IllegalArgumentException("UML Model can not be null.");
		
		builder.addImportNamespace(ARTIST_StaticPropertyCatalogue.Element.CATALOGUE);
		Observation observation = builder.addObservation("StaticAnalysis", ObservationLevel.Model, MeasurementType.Measured);
		
		builder.addDataPoint(observation, 
				builder.createMeasurementName(observation),
				ARTIST_StaticPropertyCatalogue.Element.PROGRAMMING_LANGUAGE,
				umlModel,
				builder.createValue(ARTIST_SimpleTypes.Element.PROGRAMMING_LANGUAGE_LITERALS.JAVA));		
		
		try {
			URI uri = umlModel.eResource().getURI();
			String platformString = uri.toPlatformString(true);
			
			IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
			File file = new File(resource.getLocationURI());
			
			UMLExplorer explorer = new UMLExplorer();
			
			builder.addDataPoint(observation, 
					builder.createMeasurementName(observation),
					ARTIST_NFPCatalogue.Element.COUPLING,
					umlModel,
					builder.createValue(explorer.getAssociationAvg(file)));
			
			builder.addDataPoint(observation, 
					builder.createMeasurementName(observation),
					ARTIST_NFPCatalogue.Element.COMPOSITION,
					umlModel,
					builder.createValue(explorer.getAggregationAvg(file)));
			
			builder.addDataPoint(observation, 
					builder.createMeasurementName(observation),
					ARTIST_NFPCatalogue.Element.MESSAGING,
					umlModel,
					builder.createValue(explorer.getAttributeAvg(file)));
			
			builder.addDataPoint(observation, 
					builder.createMeasurementName(observation),
					ARTIST_NFPCatalogue.Element.DESIGN_SIZE,
					umlModel,
					builder.createValue(explorer.getClassNum(file)));
			
			builder.addDataPoint(observation, 
					builder.createMeasurementName(observation),
					ARTIST_NFPCatalogue.Element.AGGREGATIONS,
					umlModel,
					builder.createValue(explorer.getAggregationNum(file)));
			
			builder.addDataPoint(observation, 
					builder.createMeasurementName(observation),
					ARTIST_NFPCatalogue.Element.ASSOCIATIONS,
					umlModel,
					builder.createValue(explorer.getAssociationNum(file)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		builder.addDataPoint(observation, 
//				builder.createMeasurementName(observation),
//				ARTIST_StaticPropertyCatalogue.Element.CLOUD_PROVIDER,
//				umlModel,
//				builder.createValue(ARTIST_SimpleTypes.Element.CLOUD_PROVIDER_LITERALS.GOOGLE_APP_ENGINE));
			
		return builder;
	}
}
