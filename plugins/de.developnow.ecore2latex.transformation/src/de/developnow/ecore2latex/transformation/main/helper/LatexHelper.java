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
package de.developnow.ecore2latex.transformation.main.helper;

import de.developnow.ecore2latex.preferences.PreferenceHelper;

public class LatexHelper extends AbstractLatexHelper{

	@Override
	public void init() {
		prefs = PreferenceHelper.getPreferenceHelper();
	}

}
