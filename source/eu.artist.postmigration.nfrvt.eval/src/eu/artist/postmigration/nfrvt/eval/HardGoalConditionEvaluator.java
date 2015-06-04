/*******************************************************************************
 * Copyright (c) 2014 Vienna University of Technology.
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
package eu.artist.postmigration.nfrvt.eval;

import eu.artist.postmigration.nfrvt.lang.common.artistCommon.ValueSpecification;
import eu.artist.postmigration.nfrvt.lang.common.eval.EvaluationSettings;
import eu.artist.postmigration.nfrvt.lang.common.eval.util.ValueUtil;
import eu.artist.postmigration.nfrvt.lang.gel.gel.AppliedPropertyEvaluation;
import eu.artist.postmigration.nfrvt.lang.gel.gel.ValueExpressionEvaluation;
import eu.artist.postmigration.nfrvt.lang.gml.gml.AppliedQuantitativePropertyExpression;
import eu.artist.postmigration.nfrvt.lang.util.MigrationFactory;

/**
 * An evaluator for hard goal conditions that uses the evaluation 
 * results from the evaluation strategies.
 * 
 * @author Martin Fleck
 *
 */
class HardGoalConditionEvaluator extends ExpressionEvaluator {

	private GoalModelEvaluator goalModelEvaluator;
	
	/**
	 * Evaluator for hard goal conditions.
	 * 
	 * @param goalModelEvaluator evaluator containing applied property evaluations
	 * @param settings settings to be considered during evaluation
	 */
	public HardGoalConditionEvaluator(GoalModelEvaluator goalModelEvaluator, EvaluationSettings settings) {
		super(settings);
		this.setGoalModelEvaluator(goalModelEvaluator);
	}

	/**
	 * Returns the goal model evaluator used.
	 * 
	 * @return used goal model evaluator
	 */
	public GoalModelEvaluator getGoalModelEvaluator() {
		return goalModelEvaluator;
	}

	/**
	 * Sets the goal model evaluator, for which the top level goals should be
	 * set.
	 * 
	 * @param goalModelEvaluator goal model evaluator
	 */
	protected void setGoalModelEvaluator(GoalModelEvaluator goalModelEvaluator) {
		this.goalModelEvaluator = goalModelEvaluator;
	}
	
	/**
	 * Evaluates the given applied property expression by checking the value
	 * for the property in the goal model evaluator.
	 * 
	 * @param reference reference to be evaluated
	 * @return evaluation result
	 */
	public ValueExpressionEvaluation evaluate(AppliedQuantitativePropertyExpression reference) {		
		AppliedPropertyEvaluation propertyEvaluation = getGoalModelEvaluator().getAppliedPropertyEvaluation(reference.getValue());
		if(propertyEvaluation == null) 
			return MigrationFactory.createNumberExpressionEvaluation(
					"[" + renderer.doRender(reference.getValue()) + "] has no value assigned.");
		
		ValueSpecification value = propertyEvaluation.getValue();
		if(ValueUtil.isBoolean(value))
			return MigrationFactory.createBooleanExpressionEvaluation(
					ValueUtil.assertBoolean(value), 
					"$" + reference.getValue().getName() + " as evaluated.");
		
		if(ValueUtil.isNumber(value))
			return MigrationFactory.createNumberExpressionEvaluation(
					ValueUtil.assertNumber(value), 
					"$" + reference.getValue().getName() + " as evaluated.");
		
		return MigrationFactory.createValueSpecificationExpressionEvaluation(
				MigrationFactory.copy(value),
				"$" + reference.getValue().getName() + " as evaluated.");
	}
}
