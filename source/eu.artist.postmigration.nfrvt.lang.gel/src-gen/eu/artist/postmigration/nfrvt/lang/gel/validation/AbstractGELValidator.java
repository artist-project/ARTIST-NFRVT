/*
 * generated by Xtext
 */
package eu.artist.postmigration.nfrvt.lang.gel.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators= {org.eclipse.xtext.validation.NamesAreUniqueValidator.class})
public class AbstractGELValidator extends eu.artist.postmigration.nfrvt.lang.common.validation.ARTISTCommonValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(eu.artist.postmigration.nfrvt.lang.gel.gel.GelPackage.eINSTANCE);
		return result;
	}
}