/*******************************************************************************
 * Copyright (c) 2012 Jacob Geisel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jacob Geisel
 *******************************************************************************/
package de.developnow.ecore2latex.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class E2LPreferencePage
extends FieldEditorPreferencePage
implements IWorkbenchPreferencePage {

	public E2LPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("A demonstration of a preference page implementation");
	}

	public void createFieldEditors() {
		addField(
				new StringFieldEditor(PreferenceConstants.P_NAMESPACE, "The Namespace for the &Annotation:", getFieldEditorParent()));
		addField(
				new StringFieldEditor(PreferenceConstants.P_DESCRIPTION_KEY, "The key for &Description:", getFieldEditorParent()));
		addField(
				new StringFieldEditor(PreferenceConstants.P_SEMANTICS_KEY, "The key for &Semantics:", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
	}

}