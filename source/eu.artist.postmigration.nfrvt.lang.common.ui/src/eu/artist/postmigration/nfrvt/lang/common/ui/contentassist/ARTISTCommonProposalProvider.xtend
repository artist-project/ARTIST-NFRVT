/*
 * generated by Xtext
 */
package eu.artist.postmigration.nfrvt.lang.common.ui.contentassist

import com.google.common.collect.Sets
import eu.artist.postmigration.nfrvt.lang.common.artistCommon.ArtistCommonFactory
import eu.artist.postmigration.nfrvt.lang.common.artistCommon.ObjectSpecificationExpression
import eu.artist.postmigration.nfrvt.lang.common.artistCommon.Tuple
import java.lang.reflect.InvocationTargetException
import java.math.BigDecimal
import java.util.Date
import java.util.Set
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.swt.graphics.Image
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import org.eclipse.xtext.ui.editor.contentassist.PrefixMatcher
import org.eclipse.xtext.util.Strings

/**
 * see http://www.eclipse.org/Xtext/documentation.html#contentAssist on how to customize content assistant
 */
class ARTISTCommonProposalProvider extends AbstractARTISTCommonProposalProvider {
	
	// the following keywords are filtered because they are keywords starting 
	// a numeric function and for those functions proposals are already 
	// provided
	Set<String> filteredKeywords = Sets.newHashSet();
	
	static val factory = ArtistCommonFactory.eINSTANCE;
	
	new() {
		filteredKeywords = Sets.newHashSet("max", "min", "avg", "sum", "abs", "exp", "ln", "log", "*"); 
	}
	
	def filterKeyword(String keyword) {
		filteredKeywords.add(keyword);
	}
	
	def void createStringProposal(ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			RuleCall ruleCall) {
		var proposalText = ruleCall.feature;
		if(proposalText == null) 
			proposalText = Strings.toFirstUpper(ruleCall.getRule().getName().toLowerCase());
		createProposal(context, acceptor, ruleCall, proposalText, STRING_DUMMY.image, 1, -2);
	}
	
	def void createStringProposal(ContentAssistContext context, ICompletionProposalAcceptor acceptor, Assignment assignment, String proposal) {
		createProposal(context, acceptor, assignment.terminal as RuleCall, proposal, STRING_DUMMY.image, 1, -2);
	}
	
	def void createIDProposal(ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			RuleCall ruleCall) {
		var proposalText = ruleCall.feature;
		if(proposalText == null) 
			proposalText = Strings.toFirstUpper(ruleCall.getRule().getName().toLowerCase());
		createIDProposal(context, acceptor, ruleCall, proposalText);
	}
	
	def void createIDProposal(ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			RuleCall ruleCall, String proposalText) {
		val newMatcher = new PrefixMatcher() {
			override boolean isCandidateMatchingPrefix(String name, String prefix) {
				var strippedName = name;
				if (name.startsWith("^") && !prefix.startsWith("^")) {
					strippedName = name.substring(1);
				}
				return context.getMatcher().isCandidateMatchingPrefix(strippedName, prefix);
			}
		};
		val myContext = context.copy().setMatcher(newMatcher).toContext();
		createProposal(myContext, acceptor, ruleCall, proposalText);
	}
	
	def String feature(RuleCall call) {
		var ass = GrammarUtil.containingAssignment(call);
		if (ass != null) {
			var result = ass.getFeature();
			if (result.equals(result.toLowerCase()))
				result = Strings.toFirstUpper(result);
			return result;
		}
		return null;
	}
	
	def void createProposal(ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			RuleCall ruleCall, Object o) {
		createProposal(context, acceptor, ruleCall, o, 0, 0);
	}
	
	def void createProposal(ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			RuleCall ruleCall, Object o, int selectionStartOffset, int selectionLengthAddition) {
		createProposal(context, acceptor, ruleCall, o, DUMMY_KEYWORD.image, selectionStartOffset, selectionLengthAddition);
	}
	
	def void createProposal(ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			RuleCall ruleCall, Object o, Image image) {
		createProposal(context, acceptor, ruleCall, o, image, 0, 0);
	}
	
	def String displayName(RuleCall call) {
		val upperCase = call.rule.name.toUpperCase
		if(upperCase == call.rule.name)
			return Strings.toFirstUpper(call.rule.name.toLowerCase)
		return call.rule.name
	}
	
	def void createProposal(ContentAssistContext context, ICompletionProposalAcceptor acceptor,
			RuleCall ruleCall, Object o, Image image, int selectionStartOffset, int selectionLengthAddition) {
		val feature = ruleCall.feature
		val ruleName = ruleCall.displayName
		var proposalText = getValueConverter().toString(o, ruleCall.getRule().getName());
		var displayText = proposalText + " - " + ruleName
		if (feature != null)
			displayText = proposalText + " - " + feature + " (" + ruleName + ")";
		var proposal = createCompletionProposal(proposalText, displayText, image, context);
		if (proposal instanceof ConfigurableCompletionProposal) {
			var configurable = proposal as ConfigurableCompletionProposal;
			configurable.setSelectionStart(configurable.getReplacementOffset() + selectionStartOffset);
			configurable.setSelectionLength(proposalText.length() + selectionLengthAddition);
			configurable.setAutoInsertable(false);
			configurable.setSimpleLinkedMode(context.getViewer(), '\t', ' ');
		}
		acceptor.accept(proposal);		
	}
	
	override completePropertyValuePair_Property(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if(!(model instanceof Tuple)) {
			super.completePropertyValuePair_Property(model, assignment, context, acceptor)
			return;
		}
		
		val tuple = model as Tuple
		val spec = tuple.eContainer as ObjectSpecificationExpression
		
		spec.type.allAttributes.forEach[p | acceptor.accept(createCompletionProposal(p.name, p.name + " : " + p.type.name, p.image, context))]
	}
	
	override complete_STRING(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createStringProposal(context, acceptor, ruleCall)
	}
	
	override complete_Number(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, new BigDecimal("1"), NUMBER_DUMMY.image);
	}
	
	override complete_EBIGDECIMAL(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, new BigDecimal("1.0"), NUMBER_DUMMY.image);
	}
	
	override complete_SMALL_DECIMAL(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, new BigDecimal("-0.1"), NUMBER_DUMMY.image);
	}
	
	override complete_POSITIVE_SMALL_DECIMAL(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, new BigDecimal("0.1"), NUMBER_DUMMY.image);
	}
	
	override complete_NullLiteral(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "null", NULL_DUMMY.image);
	}
	
	override complete_EBOOLEAN(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, true, BOOLEAN_DUMMY.image);
	}
	
	override complete_EBooleanObject(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, true, BOOLEAN_DUMMY.image);
	}
	
	override complete_Impact(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, 0.2, NUMBER_DUMMY.image);
	}
	
//	override complete_BooleanLiteral(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
//		createProposal(context, acceptor, ruleCall, "true");
//	}
	
	override complete_UnlimitedLiteral(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "*", UNLIMITED_DUMMY.image);
	}
	
	override complete_QualifiedName(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createIDProposal(context, acceptor, ruleCall, "unique.qualified.name");
	}
	
	override complete_QualifiedNameWithWildcard(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createIDProposal(context, acceptor, ruleCall, "unique.qualified.name.*");
	}
	
	override complete_ID(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createIDProposal(context, acceptor, ruleCall, "UniqueID");
	}
	
	override complete_INT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, 1, NUMBER_DUMMY.image);
	}
	
	override complete_MaximumFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "max(1, 2, 3)", NUMBER_FUNCTION_DUMMY.image, 4, -5);
	}
	
	override complete_MinimumFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "min(1, 2, 3)", NUMBER_FUNCTION_DUMMY.image, 4, -5);
	}
	
	override complete_AverageFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "avg(1, 2, 3)", NUMBER_FUNCTION_DUMMY.image, 4, -5);
	}
	
	override complete_SumFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "sum(1, 2, 3)", NUMBER_FUNCTION_DUMMY.image, 4, -5);
	}
	
	override complete_ExponentialFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "exp(2, 3)", NUMBER_FUNCTION_DUMMY.image, 4, -5);
	}
	
	override complete_AbsoluteFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "abs(-1)", NUMBER_FUNCTION_DUMMY.image, 4, -5);
	}
	
	override complete_NaturalLogarithmFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "ln(1)", NUMBER_FUNCTION_DUMMY.image, 3, -4);
	}
	
	override complete_CommonLogarithmFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, "log(1)", NUMBER_FUNCTION_DUMMY.image, 4, -5);
	}
	
	override complete_DATE_TIME(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		createProposal(context, acceptor, ruleCall, new Date(), factory.createMaximumFunction.image);
	}
	
	override completeKeyword(Keyword keyword, ContentAssistContext contentAssistContext, ICompletionProposalAcceptor acceptor) {
		if(!filteredKeywords.contains(keyword.value))
			super.completeKeyword(keyword, contentAssistContext, acceptor)
	}
	
	override completeWorkload_Pattern(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeWorkload_Pattern(model, assignment, context, acceptor)
		createStringProposal(context, acceptor, assignment, "open")
		createStringProposal(context, acceptor, assignment, "closed")
	}
	
	protected static final val STRING_DUMMY = factory.createStringLiteral()
	
	protected static final val BOOLEAN_DUMMY = factory.createBooleanLiteral()
	
	protected static final val NULL_DUMMY = factory.createNullLiteral()
	
	protected static final val UNLIMITED_DUMMY = factory.createUnlimitedLiteral()
	
	protected static final val NUMBER_FUNCTION_DUMMY = factory.createNumberFunction()
	
	protected static final val NUMBER_DUMMY = factory.createNumberLiteral()
	
	protected static final Keyword DUMMY_KEYWORD = new Keyword() {
		
		override getValue() { return ""; }
		
		override setValue(String value) { }
		
		override getCardinality() {	return ""; }
		
		override isFirstSetPredicated() { return false; }
		
		override isPredicated() { return false; }
		
		override setCardinality(String value) {	}
		
		override setFirstSetPredicated(boolean value) {	}
		
		override setPredicated(boolean value) {	}
		
		override eAllContents() { return null;	}
		
		override eClass() { return null; }
		
		override eContainer() { return null; }
		
		override eContainingFeature() { return null; }
		
		override eContainmentFeature() { return null; }
		
		override eContents() { return null; }
		
		override eCrossReferences() { return null; }
		
		override eGet(EStructuralFeature feature) { return null; }
		
		override eGet(EStructuralFeature feature, boolean resolve) { return null; }
		
		override eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException  { return null; }
		
		override eIsProxy()  { return false; }
		
		override eIsSet(EStructuralFeature feature)  { return false; }
		
		override eResource()  { return null; }
		
		override eSet(EStructuralFeature feature, Object newValue)  {  }
		
		override eUnset(EStructuralFeature feature)  { }
		
		override eAdapters()  { return null; }
		
		override eDeliver()  { return false; }
		
		override eNotify(Notification notification)  { }
		
		override eSetDeliver(boolean deliver) { }
		
	}
	
}