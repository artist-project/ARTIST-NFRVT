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
/*
 * generated by Xtext
 */
package eu.artist.postmigration.nfrvt.lang.gml.ui;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.LanguageSpecific;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.eclipse.xtext.ui.editor.contentassist.ITemplateProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.XtextContentAssistProcessor;

import com.google.inject.Binder;

import eu.artist.postmigration.nfrvt.lang.common.ui.ARTISTCommonLanguageSpecificURIEditorOpener;

/**
 * Use this class to register components to be used within the IDE.
 */
public class GMLUiModule extends eu.artist.postmigration.nfrvt.lang.gml.ui.AbstractGMLUiModule {
	public GMLUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		String eol = System.getProperty("line.separator");
		binder.bind(String.class)
				.annotatedWith(com.google.inject.name.Names.named((XtextContentAssistProcessor.COMPLETION_AUTO_ACTIVATION_CHARS)))
				.toInstance(".:" + eol);
	}
	
	@Override
	public Class<? extends ITemplateProposalProvider> bindITemplateProposalProvider() {
		return GMLTemplateProposalProvider.class;
	}
	
	@Override
	public void configureLanguageSpecificURIEditorOpener(Binder binder) {
		if (PlatformUI.isWorkbenchRunning())
			binder.bind(IURIEditorOpener.class).annotatedWith(LanguageSpecific.class)
					.to(ARTISTCommonLanguageSpecificURIEditorOpener.class);
	}
}