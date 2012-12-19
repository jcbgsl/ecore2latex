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

import org.eclipse.jface.preference.IPreferenceStore;

import de.developnow.ecore2latex.preferences.internal.Activator;


public class PreferenceHelper {

	private IPreferenceStore store;

	public PreferenceHelper() {
		store = Activator.getDefault().getPreferenceStore();
	}

	public String getPreferenceString(String p_constant) {
		return store.getString(p_constant);
	}

	public static PreferenceHelper getPreferenceHelper() {
		return Activator.getDefault().getPreferenceHelper();
	}


}
