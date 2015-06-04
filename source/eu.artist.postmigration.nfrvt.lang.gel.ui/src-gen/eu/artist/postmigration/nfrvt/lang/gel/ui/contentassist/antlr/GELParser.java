/*
* generated by Xtext
*/
package eu.artist.postmigration.nfrvt.lang.gel.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import eu.artist.postmigration.nfrvt.lang.gel.services.GELGrammarAccess;

public class GELParser extends AbstractContentAssistParser {
	
	@Inject
	private GELGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected eu.artist.postmigration.nfrvt.lang.gel.ui.contentassist.antlr.internal.InternalGELParser createParser() {
		eu.artist.postmigration.nfrvt.lang.gel.ui.contentassist.antlr.internal.InternalGELParser result = new eu.artist.postmigration.nfrvt.lang.gel.ui.contentassist.antlr.internal.InternalGELParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getAppliedPropertyEvaluationAccess().getAlternatives(), "rule__AppliedPropertyEvaluation__Alternatives");
					put(grammarAccess.getGoalEvaluationAccess().getAlternatives(), "rule__GoalEvaluation__Alternatives");
					put(grammarAccess.getValueExpressionEvaluationAccess().getAlternatives(), "rule__ValueExpressionEvaluation__Alternatives");
					put(grammarAccess.getDisjunctionAccess().getOperatorAlternatives_1_1_0(), "rule__Disjunction__OperatorAlternatives_1_1_0");
					put(grammarAccess.getComparisonAccess().getOperatorAlternatives_1_1_0(), "rule__Comparison__OperatorAlternatives_1_1_0");
					put(grammarAccess.getBooleanUnitAccess().getAlternatives(), "rule__BooleanUnit__Alternatives");
					put(grammarAccess.getRelationalExpressionAccess().getAlternatives_1_0(), "rule__RelationalExpression__Alternatives_1_0");
					put(grammarAccess.getComparableExpressionAccess().getAlternatives(), "rule__ComparableExpression__Alternatives");
					put(grammarAccess.getAdditiveExpressionAccess().getOperatorAlternatives_1_1_0(), "rule__AdditiveExpression__OperatorAlternatives_1_1_0");
					put(grammarAccess.getMultiplicativeExpressionAccess().getOperatorAlternatives_1_1_0(), "rule__MultiplicativeExpression__OperatorAlternatives_1_1_0");
					put(grammarAccess.getNumberExpressionAccess().getAlternatives(), "rule__NumberExpression__Alternatives");
					put(grammarAccess.getNumberFunctionAccess().getAlternatives(), "rule__NumberFunction__Alternatives");
					put(grammarAccess.getValueSpecificationAccess().getAlternatives(), "rule__ValueSpecification__Alternatives");
					put(grammarAccess.getObjectSpecificationExpressionAccess().getAlternatives_1(), "rule__ObjectSpecificationExpression__Alternatives_1");
					put(grammarAccess.getLiteralValueExpressionAccess().getAlternatives(), "rule__LiteralValueExpression__Alternatives");
					put(grammarAccess.getOrOperatorAccess().getAlternatives_1(), "rule__OrOperator__Alternatives_1");
					put(grammarAccess.getXOrOperatorAccess().getAlternatives_1(), "rule__XOrOperator__Alternatives_1");
					put(grammarAccess.getAndOperatorAccess().getAlternatives_1(), "rule__AndOperator__Alternatives_1");
					put(grammarAccess.getImplicationOperatorAccess().getAlternatives_1(), "rule__ImplicationOperator__Alternatives_1");
					put(grammarAccess.getNotEqualsOperatorAccess().getAlternatives_1(), "rule__NotEqualsOperator__Alternatives_1");
					put(grammarAccess.getNotOperatorAccess().getAlternatives_1(), "rule__NotOperator__Alternatives_1");
					put(grammarAccess.getImportURIorNamespaceAccess().getAlternatives_1(), "rule__ImportURIorNamespace__Alternatives_1");
					put(grammarAccess.getNumberAccess().getAlternatives(), "rule__Number__Alternatives");
					put(grammarAccess.getImpactAccess().getAlternatives(), "rule__Impact__Alternatives");
					put(grammarAccess.getEBooleanObjectAccess().getAlternatives(), "rule__EBooleanObject__Alternatives");
					put(grammarAccess.getVerdictAccess().getAlternatives(), "rule__Verdict__Alternatives");
					put(grammarAccess.getMigrationEvaluationAccess().getGroup(), "rule__MigrationEvaluation__Group__0");
					put(grammarAccess.getTransformationAccess().getGroup(), "rule__Transformation__Group__0");
					put(grammarAccess.getTransformationAccess().getGroup_5(), "rule__Transformation__Group_5__0");
					put(grammarAccess.getTransformationAccess().getGroup_6(), "rule__Transformation__Group_6__0");
					put(grammarAccess.getTransformationAccess().getGroup_7(), "rule__Transformation__Group_7__0");
					put(grammarAccess.getTransformationAccess().getGroup_8(), "rule__Transformation__Group_8__0");
					put(grammarAccess.getAppliedQualitativePropertyEvaluationAccess().getGroup(), "rule__AppliedQualitativePropertyEvaluation__Group__0");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getGroup(), "rule__AppliedQuantitativePropertyEvaluation__Group__0");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getGroup_10(), "rule__AppliedQuantitativePropertyEvaluation__Group_10__0");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getGroup_10_3(), "rule__AppliedQuantitativePropertyEvaluation__Group_10_3__0");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getGroup_10_3_1(), "rule__AppliedQuantitativePropertyEvaluation__Group_10_3_1__0");
					put(grammarAccess.getGoalModelEvaluationAccess().getGroup(), "rule__GoalModelEvaluation__Group__0");
					put(grammarAccess.getSoftGoalEvaluationAccess().getGroup(), "rule__SoftGoalEvaluation__Group__0");
					put(grammarAccess.getHardGoalEvaluationAccess().getGroup(), "rule__HardGoalEvaluation__Group__0");
					put(grammarAccess.getCompositeGoalEvaluationAccess().getGroup(), "rule__CompositeGoalEvaluation__Group__0");
					put(grammarAccess.getValueSpecificationExpressionEvaluationAccess().getGroup(), "rule__ValueSpecificationExpressionEvaluation__Group__0");
					put(grammarAccess.getValueSpecificationExpressionEvaluationAccess().getGroup_7(), "rule__ValueSpecificationExpressionEvaluation__Group_7__0");
					put(grammarAccess.getBooleanExpressionEvaluationAccess().getGroup(), "rule__BooleanExpressionEvaluation__Group__0");
					put(grammarAccess.getBooleanExpressionEvaluationAccess().getGroup_2(), "rule__BooleanExpressionEvaluation__Group_2__0");
					put(grammarAccess.getBooleanExpressionEvaluationAccess().getGroup_5(), "rule__BooleanExpressionEvaluation__Group_5__0");
					put(grammarAccess.getBooleanExpressionEvaluationAccess().getGroup_6(), "rule__BooleanExpressionEvaluation__Group_6__0");
					put(grammarAccess.getNumberExpressionEvaluationAccess().getGroup(), "rule__NumberExpressionEvaluation__Group__0");
					put(grammarAccess.getNumberExpressionEvaluationAccess().getGroup_7(), "rule__NumberExpressionEvaluation__Group_7__0");
					put(grammarAccess.getImplicationAccess().getGroup(), "rule__Implication__Group__0");
					put(grammarAccess.getImplicationAccess().getGroup_1(), "rule__Implication__Group_1__0");
					put(grammarAccess.getDisjunctionAccess().getGroup(), "rule__Disjunction__Group__0");
					put(grammarAccess.getDisjunctionAccess().getGroup_1(), "rule__Disjunction__Group_1__0");
					put(grammarAccess.getConjunctionAccess().getGroup(), "rule__Conjunction__Group__0");
					put(grammarAccess.getConjunctionAccess().getGroup_1(), "rule__Conjunction__Group_1__0");
					put(grammarAccess.getComparisonAccess().getGroup(), "rule__Comparison__Group__0");
					put(grammarAccess.getComparisonAccess().getGroup_1(), "rule__Comparison__Group_1__0");
					put(grammarAccess.getNegationAccess().getGroup(), "rule__Negation__Group__0");
					put(grammarAccess.getRelationalExpressionAccess().getGroup(), "rule__RelationalExpression__Group__0");
					put(grammarAccess.getRelationalExpressionAccess().getGroup_1(), "rule__RelationalExpression__Group_1__0");
					put(grammarAccess.getRelationalExpressionAccess().getGroup_1_0_0(), "rule__RelationalExpression__Group_1_0_0__0");
					put(grammarAccess.getRelationalExpressionAccess().getGroup_1_0_1(), "rule__RelationalExpression__Group_1_0_1__0");
					put(grammarAccess.getRelationalExpressionAccess().getGroup_1_0_2(), "rule__RelationalExpression__Group_1_0_2__0");
					put(grammarAccess.getRelationalExpressionAccess().getGroup_1_0_3(), "rule__RelationalExpression__Group_1_0_3__0");
					put(grammarAccess.getAdditiveExpressionAccess().getGroup(), "rule__AdditiveExpression__Group__0");
					put(grammarAccess.getAdditiveExpressionAccess().getGroup_1(), "rule__AdditiveExpression__Group_1__0");
					put(grammarAccess.getMultiplicativeExpressionAccess().getGroup(), "rule__MultiplicativeExpression__Group__0");
					put(grammarAccess.getMultiplicativeExpressionAccess().getGroup_1(), "rule__MultiplicativeExpression__Group_1__0");
					put(grammarAccess.getMaximumFunctionAccess().getGroup(), "rule__MaximumFunction__Group__0");
					put(grammarAccess.getMaximumFunctionAccess().getGroup_3(), "rule__MaximumFunction__Group_3__0");
					put(grammarAccess.getMinimumFunctionAccess().getGroup(), "rule__MinimumFunction__Group__0");
					put(grammarAccess.getMinimumFunctionAccess().getGroup_3(), "rule__MinimumFunction__Group_3__0");
					put(grammarAccess.getAverageFunctionAccess().getGroup(), "rule__AverageFunction__Group__0");
					put(grammarAccess.getAverageFunctionAccess().getGroup_3(), "rule__AverageFunction__Group_3__0");
					put(grammarAccess.getSumFunctionAccess().getGroup(), "rule__SumFunction__Group__0");
					put(grammarAccess.getSumFunctionAccess().getGroup_3(), "rule__SumFunction__Group_3__0");
					put(grammarAccess.getExponentialFunctionAccess().getGroup(), "rule__ExponentialFunction__Group__0");
					put(grammarAccess.getAbsoluteFunctionAccess().getGroup(), "rule__AbsoluteFunction__Group__0");
					put(grammarAccess.getNaturalLogarithmFunctionAccess().getGroup(), "rule__NaturalLogarithmFunction__Group__0");
					put(grammarAccess.getCommonLogarithmFunctionAccess().getGroup(), "rule__CommonLogarithmFunction__Group__0");
					put(grammarAccess.getParenthesizedExpressionAccess().getGroup(), "rule__ParenthesizedExpression__Group__0");
					put(grammarAccess.getObjectSpecificationExpressionAccess().getGroup(), "rule__ObjectSpecificationExpression__Group__0");
					put(grammarAccess.getCollectionAccess().getGroup(), "rule__Collection__Group__0");
					put(grammarAccess.getCollectionAccess().getGroup_2(), "rule__Collection__Group_2__0");
					put(grammarAccess.getCollectionAccess().getGroup_2_1(), "rule__Collection__Group_2_1__0");
					put(grammarAccess.getTupleAccess().getGroup(), "rule__Tuple__Group__0");
					put(grammarAccess.getTupleAccess().getGroup_2(), "rule__Tuple__Group_2__0");
					put(grammarAccess.getTupleAccess().getGroup_2_1(), "rule__Tuple__Group_2_1__0");
					put(grammarAccess.getPropertyValuePairAccess().getGroup(), "rule__PropertyValuePair__Group__0");
					put(grammarAccess.getOrOperatorAccess().getGroup(), "rule__OrOperator__Group__0");
					put(grammarAccess.getXOrOperatorAccess().getGroup(), "rule__XOrOperator__Group__0");
					put(grammarAccess.getAndOperatorAccess().getGroup(), "rule__AndOperator__Group__0");
					put(grammarAccess.getImplicationOperatorAccess().getGroup(), "rule__ImplicationOperator__Group__0");
					put(grammarAccess.getEqualsOperatorAccess().getGroup(), "rule__EqualsOperator__Group__0");
					put(grammarAccess.getNotEqualsOperatorAccess().getGroup(), "rule__NotEqualsOperator__Group__0");
					put(grammarAccess.getNotOperatorAccess().getGroup(), "rule__NotOperator__Group__0");
					put(grammarAccess.getGreaterThanOperatorAccess().getGroup(), "rule__GreaterThanOperator__Group__0");
					put(grammarAccess.getGreaterOrEqualThanOperatorAccess().getGroup(), "rule__GreaterOrEqualThanOperator__Group__0");
					put(grammarAccess.getLessThanOperatorAccess().getGroup(), "rule__LessThanOperator__Group__0");
					put(grammarAccess.getLessOrEqualThanOperatorAccess().getGroup(), "rule__LessOrEqualThanOperator__Group__0");
					put(grammarAccess.getAdditionOperatorAccess().getGroup(), "rule__AdditionOperator__Group__0");
					put(grammarAccess.getSubstractionOperatorAccess().getGroup(), "rule__SubstractionOperator__Group__0");
					put(grammarAccess.getMultiplicationOperatorAccess().getGroup(), "rule__MultiplicationOperator__Group__0");
					put(grammarAccess.getDivisionOperatorAccess().getGroup(), "rule__DivisionOperator__Group__0");
					put(grammarAccess.getModulusOperatorAccess().getGroup(), "rule__ModulusOperator__Group__0");
					put(grammarAccess.getMaxOperatorAccess().getGroup(), "rule__MaxOperator__Group__0");
					put(grammarAccess.getMinOperatorAccess().getGroup(), "rule__MinOperator__Group__0");
					put(grammarAccess.getAvgOperatorAccess().getGroup(), "rule__AvgOperator__Group__0");
					put(grammarAccess.getSumOperatorAccess().getGroup(), "rule__SumOperator__Group__0");
					put(grammarAccess.getExponentialOperatorAccess().getGroup(), "rule__ExponentialOperator__Group__0");
					put(grammarAccess.getAbsoluteOperatorAccess().getGroup(), "rule__AbsoluteOperator__Group__0");
					put(grammarAccess.getNaturalLogarithmOperatorAccess().getGroup(), "rule__NaturalLogarithmOperator__Group__0");
					put(grammarAccess.getCommonLogarithmOperatorAccess().getGroup(), "rule__CommonLogarithmOperator__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup_1(), "rule__QualifiedName__Group_1__0");
					put(grammarAccess.getWorkloadAccess().getGroup(), "rule__Workload__Group__0");
					put(grammarAccess.getQualifiedNameWithWildcardAccess().getGroup(), "rule__QualifiedNameWithWildcard__Group__0");
					put(grammarAccess.getImportURIorNamespaceAccess().getGroup(), "rule__ImportURIorNamespace__Group__0");
					put(grammarAccess.getImportURIAccess().getGroup(), "rule__ImportURI__Group__0");
					put(grammarAccess.getImportNamespaceAccess().getGroup(), "rule__ImportNamespace__Group__0");
					put(grammarAccess.getMigrationEvaluationAccess().getImportsAssignment_0(), "rule__MigrationEvaluation__ImportsAssignment_0");
					put(grammarAccess.getMigrationEvaluationAccess().getNameAssignment_2(), "rule__MigrationEvaluation__NameAssignment_2");
					put(grammarAccess.getMigrationEvaluationAccess().getDateAssignment_5(), "rule__MigrationEvaluation__DateAssignment_5");
					put(grammarAccess.getMigrationEvaluationAccess().getTransformationsAssignment_9(), "rule__MigrationEvaluation__TransformationsAssignment_9");
					put(grammarAccess.getMigrationEvaluationAccess().getPropertyEvaluationsAssignment_14(), "rule__MigrationEvaluation__PropertyEvaluationsAssignment_14");
					put(grammarAccess.getMigrationEvaluationAccess().getEvaluationAssignment_17(), "rule__MigrationEvaluation__EvaluationAssignment_17");
					put(grammarAccess.getTransformationAccess().getNameAssignment_1(), "rule__Transformation__NameAssignment_1");
					put(grammarAccess.getTransformationAccess().getPatternAssignment_4(), "rule__Transformation__PatternAssignment_4");
					put(grammarAccess.getTransformationAccess().getSourceAssignment_5_3(), "rule__Transformation__SourceAssignment_5_3");
					put(grammarAccess.getTransformationAccess().getTargetAssignment_6_3(), "rule__Transformation__TargetAssignment_6_3");
					put(grammarAccess.getTransformationAccess().getContextAssignment_7_3(), "rule__Transformation__ContextAssignment_7_3");
					put(grammarAccess.getTransformationAccess().getInfoAssignment_8_2(), "rule__Transformation__InfoAssignment_8_2");
					put(grammarAccess.getAppliedQualitativePropertyEvaluationAccess().getNameAssignment_1(), "rule__AppliedQualitativePropertyEvaluation__NameAssignment_1");
					put(grammarAccess.getAppliedQualitativePropertyEvaluationAccess().getPropertyAssignment_4(), "rule__AppliedQualitativePropertyEvaluation__PropertyAssignment_4");
					put(grammarAccess.getAppliedQualitativePropertyEvaluationAccess().getValueAssignment_7(), "rule__AppliedQualitativePropertyEvaluation__ValueAssignment_7");
					put(grammarAccess.getAppliedQualitativePropertyEvaluationAccess().getEvaluationAssignment_9(), "rule__AppliedQualitativePropertyEvaluation__EvaluationAssignment_9");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getNameAssignment_1(), "rule__AppliedQuantitativePropertyEvaluation__NameAssignment_1");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getPropertyAssignment_4(), "rule__AppliedQuantitativePropertyEvaluation__PropertyAssignment_4");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getValueAssignment_7(), "rule__AppliedQuantitativePropertyEvaluation__ValueAssignment_7");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getEvaluationAssignment_9(), "rule__AppliedQuantitativePropertyEvaluation__EvaluationAssignment_9");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getMeasurementsAssignment_10_3_0(), "rule__AppliedQuantitativePropertyEvaluation__MeasurementsAssignment_10_3_0");
					put(grammarAccess.getAppliedQuantitativePropertyEvaluationAccess().getMeasurementsAssignment_10_3_1_1(), "rule__AppliedQuantitativePropertyEvaluation__MeasurementsAssignment_10_3_1_1");
					put(grammarAccess.getGoalModelEvaluationAccess().getGoalModelAssignment_3(), "rule__GoalModelEvaluation__GoalModelAssignment_3");
					put(grammarAccess.getGoalModelEvaluationAccess().getVerdictAssignment_6(), "rule__GoalModelEvaluation__VerdictAssignment_6");
					put(grammarAccess.getGoalModelEvaluationAccess().getReasonAssignment_9(), "rule__GoalModelEvaluation__ReasonAssignment_9");
					put(grammarAccess.getGoalModelEvaluationAccess().getEvaluationsAssignment_13(), "rule__GoalModelEvaluation__EvaluationsAssignment_13");
					put(grammarAccess.getSoftGoalEvaluationAccess().getNameAssignment_1(), "rule__SoftGoalEvaluation__NameAssignment_1");
					put(grammarAccess.getSoftGoalEvaluationAccess().getGoalAssignment_4(), "rule__SoftGoalEvaluation__GoalAssignment_4");
					put(grammarAccess.getSoftGoalEvaluationAccess().getVerdictAssignment_7(), "rule__SoftGoalEvaluation__VerdictAssignment_7");
					put(grammarAccess.getSoftGoalEvaluationAccess().getReasonAssignment_10(), "rule__SoftGoalEvaluation__ReasonAssignment_10");
					put(grammarAccess.getSoftGoalEvaluationAccess().getDifferenceAssignment_13(), "rule__SoftGoalEvaluation__DifferenceAssignment_13");
					put(grammarAccess.getHardGoalEvaluationAccess().getNameAssignment_1(), "rule__HardGoalEvaluation__NameAssignment_1");
					put(grammarAccess.getHardGoalEvaluationAccess().getGoalAssignment_4(), "rule__HardGoalEvaluation__GoalAssignment_4");
					put(grammarAccess.getHardGoalEvaluationAccess().getVerdictAssignment_7(), "rule__HardGoalEvaluation__VerdictAssignment_7");
					put(grammarAccess.getHardGoalEvaluationAccess().getReasonAssignment_10(), "rule__HardGoalEvaluation__ReasonAssignment_10");
					put(grammarAccess.getHardGoalEvaluationAccess().getConditionEvaluationAssignment_13(), "rule__HardGoalEvaluation__ConditionEvaluationAssignment_13");
					put(grammarAccess.getCompositeGoalEvaluationAccess().getNameAssignment_1(), "rule__CompositeGoalEvaluation__NameAssignment_1");
					put(grammarAccess.getCompositeGoalEvaluationAccess().getGoalAssignment_4(), "rule__CompositeGoalEvaluation__GoalAssignment_4");
					put(grammarAccess.getCompositeGoalEvaluationAccess().getVerdictAssignment_7(), "rule__CompositeGoalEvaluation__VerdictAssignment_7");
					put(grammarAccess.getCompositeGoalEvaluationAccess().getReasonAssignment_10(), "rule__CompositeGoalEvaluation__ReasonAssignment_10");
					put(grammarAccess.getCompositeGoalEvaluationAccess().getConditionEvaluationAssignment_13(), "rule__CompositeGoalEvaluation__ConditionEvaluationAssignment_13");
					put(grammarAccess.getValueSpecificationExpressionEvaluationAccess().getResultAssignment_3(), "rule__ValueSpecificationExpressionEvaluation__ResultAssignment_3");
					put(grammarAccess.getValueSpecificationExpressionEvaluationAccess().getReasonAssignment_6(), "rule__ValueSpecificationExpressionEvaluation__ReasonAssignment_6");
					put(grammarAccess.getValueSpecificationExpressionEvaluationAccess().getEvaluationsAssignment_7_3(), "rule__ValueSpecificationExpressionEvaluation__EvaluationsAssignment_7_3");
					put(grammarAccess.getBooleanExpressionEvaluationAccess().getResultAssignment_2_1(), "rule__BooleanExpressionEvaluation__ResultAssignment_2_1");
					put(grammarAccess.getBooleanExpressionEvaluationAccess().getReasonAssignment_4(), "rule__BooleanExpressionEvaluation__ReasonAssignment_4");
					put(grammarAccess.getBooleanExpressionEvaluationAccess().getDifferenceAssignment_5_2(), "rule__BooleanExpressionEvaluation__DifferenceAssignment_5_2");
					put(grammarAccess.getBooleanExpressionEvaluationAccess().getEvaluationsAssignment_6_3(), "rule__BooleanExpressionEvaluation__EvaluationsAssignment_6_3");
					put(grammarAccess.getNumberExpressionEvaluationAccess().getResultAssignment_3(), "rule__NumberExpressionEvaluation__ResultAssignment_3");
					put(grammarAccess.getNumberExpressionEvaluationAccess().getReasonAssignment_6(), "rule__NumberExpressionEvaluation__ReasonAssignment_6");
					put(grammarAccess.getNumberExpressionEvaluationAccess().getEvaluationsAssignment_7_3(), "rule__NumberExpressionEvaluation__EvaluationsAssignment_7_3");
					put(grammarAccess.getImplicationAccess().getOperatorAssignment_1_1(), "rule__Implication__OperatorAssignment_1_1");
					put(grammarAccess.getImplicationAccess().getRightAssignment_1_2(), "rule__Implication__RightAssignment_1_2");
					put(grammarAccess.getDisjunctionAccess().getOperatorAssignment_1_1(), "rule__Disjunction__OperatorAssignment_1_1");
					put(grammarAccess.getDisjunctionAccess().getRightAssignment_1_2(), "rule__Disjunction__RightAssignment_1_2");
					put(grammarAccess.getConjunctionAccess().getOperatorAssignment_1_1(), "rule__Conjunction__OperatorAssignment_1_1");
					put(grammarAccess.getConjunctionAccess().getRightAssignment_1_2(), "rule__Conjunction__RightAssignment_1_2");
					put(grammarAccess.getComparisonAccess().getOperatorAssignment_1_1(), "rule__Comparison__OperatorAssignment_1_1");
					put(grammarAccess.getComparisonAccess().getRightAssignment_1_2(), "rule__Comparison__RightAssignment_1_2");
					put(grammarAccess.getNegationAccess().getOperatorAssignment_0(), "rule__Negation__OperatorAssignment_0");
					put(grammarAccess.getNegationAccess().getValueAssignment_1(), "rule__Negation__ValueAssignment_1");
					put(grammarAccess.getRelationalExpressionAccess().getOperatorAssignment_1_0_0_1(), "rule__RelationalExpression__OperatorAssignment_1_0_0_1");
					put(grammarAccess.getRelationalExpressionAccess().getOperatorAssignment_1_0_1_1(), "rule__RelationalExpression__OperatorAssignment_1_0_1_1");
					put(grammarAccess.getRelationalExpressionAccess().getOperatorAssignment_1_0_2_1(), "rule__RelationalExpression__OperatorAssignment_1_0_2_1");
					put(grammarAccess.getRelationalExpressionAccess().getOperatorAssignment_1_0_3_1(), "rule__RelationalExpression__OperatorAssignment_1_0_3_1");
					put(grammarAccess.getRelationalExpressionAccess().getRightAssignment_1_1(), "rule__RelationalExpression__RightAssignment_1_1");
					put(grammarAccess.getAdditiveExpressionAccess().getOperatorAssignment_1_1(), "rule__AdditiveExpression__OperatorAssignment_1_1");
					put(grammarAccess.getAdditiveExpressionAccess().getRightAssignment_1_2(), "rule__AdditiveExpression__RightAssignment_1_2");
					put(grammarAccess.getMultiplicativeExpressionAccess().getOperatorAssignment_1_1(), "rule__MultiplicativeExpression__OperatorAssignment_1_1");
					put(grammarAccess.getMultiplicativeExpressionAccess().getRightAssignment_1_2(), "rule__MultiplicativeExpression__RightAssignment_1_2");
					put(grammarAccess.getMaximumFunctionAccess().getOperatorAssignment_0(), "rule__MaximumFunction__OperatorAssignment_0");
					put(grammarAccess.getMaximumFunctionAccess().getValuesAssignment_2(), "rule__MaximumFunction__ValuesAssignment_2");
					put(grammarAccess.getMaximumFunctionAccess().getValuesAssignment_3_1(), "rule__MaximumFunction__ValuesAssignment_3_1");
					put(grammarAccess.getMinimumFunctionAccess().getOperatorAssignment_0(), "rule__MinimumFunction__OperatorAssignment_0");
					put(grammarAccess.getMinimumFunctionAccess().getValuesAssignment_2(), "rule__MinimumFunction__ValuesAssignment_2");
					put(grammarAccess.getMinimumFunctionAccess().getValuesAssignment_3_1(), "rule__MinimumFunction__ValuesAssignment_3_1");
					put(grammarAccess.getAverageFunctionAccess().getOperatorAssignment_0(), "rule__AverageFunction__OperatorAssignment_0");
					put(grammarAccess.getAverageFunctionAccess().getValuesAssignment_2(), "rule__AverageFunction__ValuesAssignment_2");
					put(grammarAccess.getAverageFunctionAccess().getValuesAssignment_3_1(), "rule__AverageFunction__ValuesAssignment_3_1");
					put(grammarAccess.getSumFunctionAccess().getOperatorAssignment_0(), "rule__SumFunction__OperatorAssignment_0");
					put(grammarAccess.getSumFunctionAccess().getValuesAssignment_2(), "rule__SumFunction__ValuesAssignment_2");
					put(grammarAccess.getSumFunctionAccess().getValuesAssignment_3_1(), "rule__SumFunction__ValuesAssignment_3_1");
					put(grammarAccess.getExponentialFunctionAccess().getOperatorAssignment_0(), "rule__ExponentialFunction__OperatorAssignment_0");
					put(grammarAccess.getExponentialFunctionAccess().getBaseAssignment_2(), "rule__ExponentialFunction__BaseAssignment_2");
					put(grammarAccess.getExponentialFunctionAccess().getExponentAssignment_4(), "rule__ExponentialFunction__ExponentAssignment_4");
					put(grammarAccess.getAbsoluteFunctionAccess().getOperatorAssignment_0(), "rule__AbsoluteFunction__OperatorAssignment_0");
					put(grammarAccess.getAbsoluteFunctionAccess().getValueAssignment_2(), "rule__AbsoluteFunction__ValueAssignment_2");
					put(grammarAccess.getNaturalLogarithmFunctionAccess().getOperatorAssignment_0(), "rule__NaturalLogarithmFunction__OperatorAssignment_0");
					put(grammarAccess.getNaturalLogarithmFunctionAccess().getValueAssignment_2(), "rule__NaturalLogarithmFunction__ValueAssignment_2");
					put(grammarAccess.getCommonLogarithmFunctionAccess().getOperatorAssignment_0(), "rule__CommonLogarithmFunction__OperatorAssignment_0");
					put(grammarAccess.getCommonLogarithmFunctionAccess().getValueAssignment_2(), "rule__CommonLogarithmFunction__ValueAssignment_2");
					put(grammarAccess.getParenthesizedExpressionAccess().getValueAssignment_1(), "rule__ParenthesizedExpression__ValueAssignment_1");
					put(grammarAccess.getObjectSpecificationExpressionAccess().getTypeAssignment_0(), "rule__ObjectSpecificationExpression__TypeAssignment_0");
					put(grammarAccess.getObjectSpecificationExpressionAccess().getValueAssignment_1_0(), "rule__ObjectSpecificationExpression__ValueAssignment_1_0");
					put(grammarAccess.getObjectSpecificationExpressionAccess().getValueAssignment_1_1(), "rule__ObjectSpecificationExpression__ValueAssignment_1_1");
					put(grammarAccess.getCollectionAccess().getValuesAssignment_2_0(), "rule__Collection__ValuesAssignment_2_0");
					put(grammarAccess.getCollectionAccess().getValuesAssignment_2_1_1(), "rule__Collection__ValuesAssignment_2_1_1");
					put(grammarAccess.getTupleAccess().getTuplesAssignment_2_0(), "rule__Tuple__TuplesAssignment_2_0");
					put(grammarAccess.getTupleAccess().getTuplesAssignment_2_1_1(), "rule__Tuple__TuplesAssignment_2_1_1");
					put(grammarAccess.getPropertyValuePairAccess().getPropertyAssignment_0(), "rule__PropertyValuePair__PropertyAssignment_0");
					put(grammarAccess.getPropertyValuePairAccess().getValueAssignment_2(), "rule__PropertyValuePair__ValueAssignment_2");
					put(grammarAccess.getInstanceSpecificationExpressionAccess().getValueAssignment(), "rule__InstanceSpecificationExpression__ValueAssignment");
					put(grammarAccess.getBooleanLiteralAccess().getValueAssignment(), "rule__BooleanLiteral__ValueAssignment");
					put(grammarAccess.getNumberLiteralAccess().getValueAssignment(), "rule__NumberLiteral__ValueAssignment");
					put(grammarAccess.getNullLiteralAccess().getValueAssignment(), "rule__NullLiteral__ValueAssignment");
					put(grammarAccess.getStringLiteralAccess().getValueAssignment(), "rule__StringLiteral__ValueAssignment");
					put(grammarAccess.getUnlimitedLiteralAccess().getValueAssignment(), "rule__UnlimitedLiteral__ValueAssignment");
					put(grammarAccess.getWorkloadAccess().getNameAssignment_0(), "rule__Workload__NameAssignment_0");
					put(grammarAccess.getWorkloadAccess().getActivityAssignment_3(), "rule__Workload__ActivityAssignment_3");
					put(grammarAccess.getWorkloadAccess().getPatternAssignment_6(), "rule__Workload__PatternAssignment_6");
					put(grammarAccess.getImportURIorNamespaceAccess().getImportURIAssignment_1_0(), "rule__ImportURIorNamespace__ImportURIAssignment_1_0");
					put(grammarAccess.getImportURIorNamespaceAccess().getImportedNamespaceAssignment_1_1(), "rule__ImportURIorNamespace__ImportedNamespaceAssignment_1_1");
					put(grammarAccess.getImportURIAccess().getImportURIAssignment_1(), "rule__ImportURI__ImportURIAssignment_1");
					put(grammarAccess.getImportNamespaceAccess().getImportedNamespaceAssignment_1(), "rule__ImportNamespace__ImportedNamespaceAssignment_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			eu.artist.postmigration.nfrvt.lang.gel.ui.contentassist.antlr.internal.InternalGELParser typedParser = (eu.artist.postmigration.nfrvt.lang.gel.ui.contentassist.antlr.internal.InternalGELParser) parser;
			typedParser.entryRuleARTISTModel();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public GELGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(GELGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
