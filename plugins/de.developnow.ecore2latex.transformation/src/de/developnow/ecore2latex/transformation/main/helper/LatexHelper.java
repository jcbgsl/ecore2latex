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
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;

import de.developnow.ecore2latex.preferences.PreferenceConstants;
import de.developnow.ecore2latex.preferences.PreferenceHelper;

public class LatexHelper {

	public static String LATEXDOC = "latexdoc";
	public static String DESCRIPTION = "description";
	public static String SEMANTICS = "semantics";
	public static String EXTERNAL = "external";
	private static PreferenceHelper helper = PreferenceHelper.getPreferenceHelper();


	/**
	 * Finds an annotation of type {@link PreferenceConstants.P_NAMESPACE} of a model element and extracts 
	 * the value of the details entry with the given key
	 * @param eModelElement
	 * @param key
	 * @return A string containing the value or null if either the annotation
	 * 			doesn't exists or the key doesn't exist
	 */
	protected static String getLatexDocAnnotation(EModelElement eModelElement, String key) {
		EAnnotation annotation = eModelElement.getEAnnotation(helper.getPreferenceString(PreferenceConstants.P_NAMESPACE));
		return (annotation != null) ? annotation.getDetails().get(key) : null;
	}

	/**
	 * Gets the latexdoc description of an EPackage
	 * @param ePackage The EPackage
	 * @return The Description or an empty String
	 */
	public static String getPackageDescription(EPackage ePackage) {
		String description = getLatexDocAnnotation(ePackage, helper.getPreferenceString(PreferenceConstants.P_DESCRIPTION_KEY));
		return (description != null) ? description : "";
	}

	/**
	 * Gets the latexdoc description or a sentence saying nos description is available
	 * of an {@link EClassifier} and layouts it into a subsubsection
	 * @param eClassifier the described {@link EClassifier} 
	 * @return The subsubsection
	 */
	public static String getClassDescription(EClassifier eClassifier) {
		String description = getLatexDocAnnotation(eClassifier, DESCRIPTION);
		if (description != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\subsubsection*{Description} ~\\\\\n");
			sb.append(description);
			return sb.toString();
		}
		return "\\subsubsection*{Description} ~\\\\ No additional description";
	}

	/**
	 * Gets the latexdoc description or a sentence saying no semantics are available
	 * of an {@link EClassifier} and layouts it into a subsubsection
	 * @param eClassifier the semantically described {@link EClassifier} 
	 * @return The subsubsection
	 */
	public static String getClassSemantics(EClassifier eClassifier) {
		String semantics = getLatexDocAnnotation(eClassifier, SEMANTICS);
		if (semantics != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\subsubsection*{Semantics} ~\\\\\n");
			sb.append(semantics);
			return sb.toString();
		}
		return "\\subsubsection*{Semantics} ~\\\\ No additional description";
	}

	/**
	 * Creates a not numbered subsubsection including a list of the direct
	 * superclasses of the {@link EClass}
	 * @param eClass the {@link EClass} to check
	 * @return The subsubsection
	 */
	public static String getClassGeneralizations(EClass eClass) {
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

	/**
	 * Creates a not numbered subsubsection including a list of the attributes
	 * of the {@link EClass}
	 * @param eClass the {@link EClass} to check
	 * @return The subsubsection
	 */
	public static String getClassAttributes(EClass eClass) {
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
				if (getLatexDocAnnotation(eAttribute, DESCRIPTION) != null) {
					sb.append("\\newline\n").append(getLatexDocAnnotation(eAttribute, DESCRIPTION));
				}
				sb.append("\n");
			}//for each attribute
			sb.append("\\end{itemize}");
			return sb.toString();
		}
		return "\\subsubsection*{Attributes} ~\\\\ No additional attributes";
	}

	/**
	 * Creates a not numbered subsubsection including a list of the associations
	 * of the {@link EClass} <br>
	 * @param eClass the {@link EClass} to check
	 * @return The subsubsection
	 */
	public static String getClassAssociations(EClass eClass) {
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
				if (getLatexDocAnnotation(eReference, DESCRIPTION) != null) {
					sb.append("\\newline\n").append(getLatexDocAnnotation(eReference, DESCRIPTION));
				}
				sb.append("\n");
			}
			sb.append("\\end{itemize}");
			return sb.toString();
		}
		return "\\subsubsection*{Associations} ~\\\\ No additional associations";
	}

	public static String getMultiplicity(ETypedElement eTypedElement) {
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
