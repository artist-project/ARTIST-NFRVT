/*
* generated by Xtext
*/
package eu.artist.postmigration.nfrvt.lang.nsl.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class NSLAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("eu/artist/postmigration/nfrvt/lang/nsl/parser/antlr/internal/InternalNSL.tokens");
	}
}