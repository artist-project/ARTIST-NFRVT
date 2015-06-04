/*
 * generated by Xtext
 */
package eu.artist.postmigration.nfrvt.lang.nsl.validation

import eu.artist.postmigration.nfrvt.lang.nsl.nsl.DerivedQuantitativeProperty
import eu.artist.postmigration.nfrvt.lang.nsl.nsl.NslPackage
import eu.artist.postmigration.nfrvt.lang.nsl.nsl.Property
import eu.artist.postmigration.nfrvt.lang.nsl.nsl.QualitativeProperty
import eu.artist.postmigration.nfrvt.lang.nsl.nsl.QuantitativePropertyExpression
import java.util.HashSet
import java.util.Set
import org.eclipse.xtext.validation.Check
import eu.artist.postmigration.nfrvt.lang.common.eval.util.ValueUtil
import eu.artist.postmigration.nfrvt.lang.common.eval.ExpressionValidator
import eu.artist.postmigration.nfrvt.lang.nsl.renderer.NSLTextRenderer

//import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class NSLValidator extends AbstractNSLValidator {

//  public static val INVALID_NAME = 'invalidName'
//
	@Check
	def checkQualitativePropertySubProperties(QualitativeProperty property) {
		property.properties.forEach[p | 
			val nr = property.properties.filter[s | s.name.equals(p.name)].size
			if(nr > 1)
				error("Property " + p.name + " is contained " + nr + " times in referenced properties.", NslPackage.Literals.QUALITATIVE_PROPERTY__PROPERTIES)
		]
	}
	
	@Check
	def checkPropertyImpacts(Property property) {
		property.impacts.forEach[p | 
			val nr = property.impacts.filter[s | s.target.name.equals(p.target.name)].size
			if(nr > 1)
				error("Impact to property " + p.target.name + " is specified " + nr + " times.", NslPackage.Literals.PROPERTY__IMPACTS)
		]
	}
	
//	@Check
//	def circularReferenceProperties(QualitativeProperty property) {
//		if(property.checkCircle(new HashSet<QualitativeProperty>()))
//			error("Found circular reference in properties.", NslPackage.Literals.QUALITATIVE_PROPERTY__PROPERTIES)		
//	}
	
	def boolean checkCircle(QualitativeProperty property, Set<QualitativeProperty> foundProperties) {
		foundProperties.add(property)
		val subProperties = property.subQualitativeProperties
		val circle = subProperties.filter[p | foundProperties.contains(p)]
		if(circle.empty) {
			// foundProperties.addAll(subProperties)
			var foundCircle = false
			for(p : subProperties) {
				foundCircle = p.checkCircle(foundProperties)
				if(foundCircle) {
					return true
				}
			}
			return false
		}
		return true
	}
	
	def subQualitativeProperties(QualitativeProperty property) {
		return property.properties.filter(typeof(QualitativeProperty)).toSet
	}
	
	@Check
	def circularReferenceDerivedProperty(DerivedQuantitativeProperty property) {
		if(property.checkCircle(new HashSet<DerivedQuantitativeProperty>()))
			error("Found circular reference in derived properties.", NslPackage.Literals.DERIVED_QUANTITATIVE_PROPERTY__EXPRESSION)		
	}
	
	def boolean checkCircle(DerivedQuantitativeProperty property, Set<DerivedQuantitativeProperty> foundProperties) {
		foundProperties.add(property)
		val subProperties = property.subDerivedProperties
		val circle = subProperties.filter[p | foundProperties.contains(p)]
		if(circle.empty) {
			foundProperties.addAll(subProperties)
			var foundCircle = false
			for(p : subProperties) {
				foundCircle = p.checkCircle(foundProperties)
				if(foundCircle) {
					return true
				}
			}
			return false
		}
		return true
	}
	
	def subDerivedProperties(DerivedQuantitativeProperty property) {
		return property.expression.eAllContents.filter(typeof(QuantitativePropertyExpression)).map[e | e.value].filter(typeof(DerivedQuantitativeProperty)).toSet
	}
	
	@Check
	def checkPropertiesUsed(DerivedQuantitativeProperty property) {
		val result = new ExpressionValidator(new NSLTextRenderer()).doEvaluate(property.expression);
		val variableExpressions = property.expression.eAllContents.filter(typeof(QuantitativePropertyExpression)).toList
		if(variableExpressions.empty)
			warning("No references to any quantitative property found, always returns " + ValueUtil.getNumberOrNull(result) + ".", NslPackage.Literals.DERIVED_QUANTITATIVE_PROPERTY__EXPRESSION)
	}
}