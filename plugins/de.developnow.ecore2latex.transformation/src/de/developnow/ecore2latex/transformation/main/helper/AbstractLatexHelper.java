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

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;

import de.developnow.ecore2latex.preferences.IPreferenceHelper;
import de.developnow.ecore2latex.preferences.PreferenceConstants;

public abstract class AbstractLatexHelper {

	protected IPreferenceHelper prefs;

	public AbstractLatexHelper() {
		init();
	}
	
	public abstract void init();

	public String getLatexDocAnnotation(EModelElement eModelElement, String key) {
		EAnnotation annotation = eModelElement.getEAnnotation(prefs.getPreferenceString(PreferenceConstants.P_NAMESPACE));
		return (annotation != null) ? annotation.getDetails().get(key) : null;
	}

	public String getPackageDescription(EPackage ePackage) {
		String description = getLatexDocAnnotation(ePackage, prefs.getPreferenceString(PreferenceConstants.P_DESCRIPTION_KEY));
		return (description != null) ? description : "";
	}

	public String getClassDescription(EClass eClass) {
		String description = getLatexDocAnnotation(eClass, prefs.getPreferenceString(PreferenceConstants.P_DESCRIPTION_KEY));
		if (description != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\subsubsection*{Description} ~\\\\\n");
			sb.append(description);
			return sb.toString();
		}
		return "\\subsubsection*{Description} ~\\\\ No additional description";

	}

	public String getClassSemantics(EClass eClass) {
		String semantics = getLatexDocAnnotation(eClass, prefs.getPreferenceString(PreferenceConstants.P_SEMANTICS_KEY));
		if (semantics != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\subsubsection*{Semantics} ~\\\\\n");
			sb.append(semantics);
			return sb.toString();
		}
		return "\\subsubsection*{Semantics} ~\\\\ No additional description";
	}

	public String getClassGeneralizations(EClass eClass) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\subsubsection*{Generalizations}\n");
		sb.append("\\begin{itemize}\n");
		if (eClass.getESuperTypes().size() > 0) {
			for (EClass superclass : eClass.getESuperTypes()) {
				sb.append("\\item \\nameref{").append(superclass.getName()).append("}\n");
			}
		} else {
			sb.append("\\item None\n");
		}
		sb.append("\\end{itemize}");
		return sb.toString();
	}

	public String getClassAttributes(EClass eClass) {
		//if attributes exist
		if (eClass.getEAttributes().size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\subsubsection*{Attributes}\n");
			sb.append("\\begin{itemize}\n");
			//for each attribute
			for (EAttribute eAttribute : eClass.getEAttributes()) {
				sb.append("\\item ");
				//Name
				sb.append(eAttribute.getName()).append(": ");
				//Type
				if (eAttribute.getEAttributeType().getInstanceClass() != null) {
					sb.append(eAttribute.getEAttributeType().getInstanceClass().getSimpleName()).append(" ");				
				} else {
					sb.append("\\nameref{").append(eAttribute.getEAttributeType().getName()).append("} ");
				}
				//Mult
				sb.append(getMultiplicity(eAttribute)).append(" ");
				//Default Value
				if (eAttribute.getDefaultValue() != null) {
					sb.append(" \\textit{~=~").append(eAttribute.getDefaultValue()).append("} ");
				}
				//Description
				if (getLatexDocAnnotation(eAttribute, prefs.getPreferenceString(PreferenceConstants.P_DESCRIPTION_KEY)) != null) {
					sb.append("\\newline\n").append(getLatexDocAnnotation(eAttribute, prefs.getPreferenceString(PreferenceConstants.P_DESCRIPTION_KEY)));
				}
				sb.append("\n");
			}//for each attribute
			sb.append("\\end{itemize}");
			return sb.toString();
		}
		return "\\subsubsection*{Attributes} ~\\\\ No additional attributes";
	}

	public String getClassAssociations(EClass eClass) {
		//if associations exist
		if (eClass.getEReferences().size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\subsubsection*{Associations}\n");
			sb.append("\\begin{itemize}\n");
			for (EReference eReference : eClass.getEReferences()) {
				sb.append("\\item ");
				//Name
				sb.append(eReference.getName()).append(": ");
				//Type
				//Works only for internal
				sb.append("\\nameref{").append(eReference.getEReferenceType().getName()).append("} ");
				//Mult
				sb.append(getMultiplicity(eReference)).append(" ");
				//Containement
				if (eReference.isContainment()) {
					sb.append("(containment) ");
				}
				//Description
				if (getLatexDocAnnotation(eReference, prefs.getPreferenceString(PreferenceConstants.P_DESCRIPTION_KEY)) != null) {
					sb.append("\\newline\n").append(getLatexDocAnnotation(eReference, prefs.getPreferenceString(PreferenceConstants.P_DESCRIPTION_KEY)));
				}
				sb.append("\n");
			}
			sb.append("\\end{itemize}");
			return sb.toString();
		}
		return "\\subsubsection*{Associations} ~\\\\ No additional associations";

	}

	public String getMultiplicity(ETypedElement eTypedElement) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		//if only one multiplicity
		if (eTypedElement.getLowerBound() == eTypedElement.getUpperBound()) {
			sb.append(eTypedElement.getLowerBound());
		} 
		//if more than one multiplicity
		else {
			sb.append(eTypedElement.getLowerBound());
			sb.append("..");
			//if upper bound is -1 replace it
			if (eTypedElement.getUpperBound() == -1) {
				sb.append("*");
			} else {
				sb.append(eTypedElement.getUpperBound());
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
