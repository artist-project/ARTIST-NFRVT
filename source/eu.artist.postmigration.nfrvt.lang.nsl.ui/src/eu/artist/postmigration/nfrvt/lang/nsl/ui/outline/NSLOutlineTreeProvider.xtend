/*
* generated by Xtext
*/
package eu.artist.postmigration.nfrvt.lang.nsl.ui.outline

import eu.artist.postmigration.nfrvt.lang.common.ui.outline.ARTISTCommonOutlineTreeProvider
import eu.artist.postmigration.nfrvt.lang.nsl.nsl.PropertyCatalogue
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.ui.editor.outline.IOutlineNode

/**
 * Customization of the default outline structure.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#outline
 */
class NSLOutlineTreeProvider extends ARTISTCommonOutlineTreeProvider {
	def protected _createChildren(IOutlineNode parentNode, PropertyCatalogue modelElement) {
		for (EObject childElement : modelElement.properties)
			createNode(parentNode, childElement);
	}
}