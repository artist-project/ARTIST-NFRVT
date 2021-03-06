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
package at.ac.tuwien.big.moea.search.solution.generator;

import org.moeaframework.core.Problem;
import org.moeaframework.core.Variable;
import org.moeaframework.core.operator.RandomInitialization;

import at.ac.tuwien.big.moea.util.random.IRandomizable;

public class ExtendedRandomInitialization extends RandomInitialization {
	
	private int populationSize;
	
	public ExtendedRandomInitialization(Problem problem, int populationSize) {
		super(problem, populationSize);
		this.populationSize = populationSize;
	}
	
	public int getPopulationSize() {
		return populationSize;
	}
	
	@Override
	protected void initialize(Variable variable) {
		if(variable instanceof IRandomizable<?>) {
			((IRandomizable<?>)variable).randomize();
		} else {
			super.initialize(variable);
		}
	}
}
