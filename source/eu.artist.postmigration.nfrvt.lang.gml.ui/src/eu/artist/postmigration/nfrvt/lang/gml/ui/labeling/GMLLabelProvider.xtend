/*
* generated by Xtext
*/
package eu.artist.postmigration.nfrvt.lang.gml.ui.labeling

import com.google.inject.Inject
import eu.artist.postmigration.nfrvt.lang.common.ui.labeling.ARTISTCommonLabelProvider
import eu.artist.postmigration.nfrvt.lang.gml.renderer.GMLTextRenderer
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import eu.artist.postmigration.nfrvt.lang.gml.ui.internal.GMLActivator

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#labelProvider
 */
class GMLLabelProvider extends ARTISTCommonLabelProvider {

	@Inject
	new(AdapterFactoryLabelProvider delegate) {
		super(delegate, new GMLTextRenderer(), GMLActivator.getInstance);
	}
}
