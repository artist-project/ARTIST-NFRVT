/*******************************************************************************
 * Copyright (c) 2015 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Fleck (Vienna University of Technology) - initial API and implementation
 *
 * Initially developed in the context of ARTIST EU project www.artist-project.eu
 *******************************************************************************/
package eu.artist.postmigration.nfrvt.search.run;

import at.ac.tuwien.big.moea.search.fitness.dimension.AbstractFitnessDimension;
import eu.artist.postmigration.opgml.fitness.GoalAnalysisMultiFitnessFunction;
import eu.artist.postmigration.opgml.fitness.analysis.ScenarioAnalysis;
import eu.artist.postmigration.opgml.gml.GoalModel;
import eu.artist.postmigration.opgml.variable.FederatedIdentityTemplate;
import eu.artist.postmigration.opgml.variable.IPatternTemplateVariable;
import eu.artist.postmigration.opgml.variable.PatternSelectionSolution;

public class ExtendedGoalFitnessFunction extends GoalAnalysisMultiFitnessFunction {
	public ExtendedGoalFitnessFunction(ScenarioAnalysis analysis, GoalModel goals) {
		super(analysis, goals);
	}

	@Override
	protected void initConstraintDimensions() {
		super.initConstraintDimensions();
		addConstraint(new AbstractFitnessDimension<PatternSelectionSolution>(PatternSelectionSolution.class, "SingleFederatedIdentity") {
			@Override
			public double evaluate(PatternSelectionSolution solution) {
				boolean exists = false;
				for(IPatternTemplateVariable config : solution.getNonEmptyConfigurations())
					if(config instanceof FederatedIdentityTemplate) {
						if(exists)
							return CONSTRAINT_VIOLATED;
						exists = true;
					}
				return CONSTRAINT_OK;
			}
		});
	}
	
	@Override
	protected void initObjectiveDimensions() {
		super.initObjectiveDimensions();
		addObjective(new AbstractFitnessDimension<PatternSelectionSolution>(
				PatternSelectionSolution.class, "Security") {
			@Override
			public double evaluate(PatternSelectionSolution solution) {
				int federatedIdentity = 0;
				for(IPatternTemplateVariable config : solution.getNonEmptyConfigurations())
					if(config instanceof FederatedIdentityTemplate) {
						federatedIdentity++;
					}
				return 0.2 - (federatedIdentity * 0.2);
			}
		});
		removeObjective("Cost");
	}
}
