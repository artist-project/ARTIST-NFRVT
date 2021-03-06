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
package at.ac.tuwien.big.moea.search.fitness.comparator;

import org.moeaframework.core.Solution;

import at.ac.tuwien.big.moea.problem.solution.MOEASolution;
import at.ac.tuwien.big.moea.util.MathUtil;

public class AggregatedFitnessComparator<S extends Solution> extends AttributeFitnessComparator<Double, S> {

	public AggregatedFitnessComparator() {
		super(MOEASolution.ATTRIBUTE_AGGREGATED_FITNESS, Double.class);
	}

	@Override
	public Double getValue(S solution) {
		Double fitness = super.getValue(solution);
		if(fitness == null)
			fitness = MathUtil.getSum(solution.getObjectives(), solution.getConstraints());
		return fitness;
	}
}
