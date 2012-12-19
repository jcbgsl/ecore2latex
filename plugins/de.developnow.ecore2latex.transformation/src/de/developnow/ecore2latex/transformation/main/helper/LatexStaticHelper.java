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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;

public class LatexStaticHelper {

	private static AbstractLatexHelper helper;

	public static AbstractLatexHelper getHelper() {
		if (helper == null) {
			helper = new LatexHelper();
		}
		return helper;
	}

	public static void setHelper(AbstractLatexHelper latexHelper) {
		helper = latexHelper;
	}

	protected static String staticGetLatexDocAnnotation(EModelElement eModelElement, String key) {
		return getHelper().getLatexDocAnnotation(eModelElement, key);
	}

	public static String getPackageDescription(EPackage ePackage) {
		return getHelper().getPackageDescription(ePackage);
	}

	public static String getClassDescription(EClass eClass) {
		return getHelper().getClassDescription(eClass);
	}

	public static String getClassSemantics(EClass eClass) {
		return getHelper().getClassSemantics(eClass);
	}

	public static String getClassGeneralizations(EClass eClass) {
		return getHelper().getClassGeneralizations(eClass);
	}

	public static String getClassAttributes(EClass eClass) {
		return getHelper().getClassAttributes(eClass);
	}

	public static String getClassAssociations(EClass eClass) {
		return getHelper().getClassAssociations(eClass);
	}

	public static String getMultiplicity(ETypedElement eTypedElement) {
		return getHelper().getMultiplicity(eTypedElement);
	}

}
