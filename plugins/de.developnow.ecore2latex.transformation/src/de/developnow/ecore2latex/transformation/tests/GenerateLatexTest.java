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
package de.developnow.ecore2latex.transformation.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;

import de.developnow.ecore2latex.preferences.IPreferenceHelper;
import de.developnow.ecore2latex.preferences.PreferenceConstants;
import de.developnow.ecore2latex.transformation.main.helper.AbstractLatexHelper;
import de.developnow.ecore2latex.transformation.main.helper.LatexStaticHelper;

public class GenerateLatexTest extends AbstractAcceleoGenerator {
    public static final String MODULE_FILE_NAME = "/de/developnow/ecore2latex/transformation/main/generateLatex";
    
    public static final String[] TEMPLATE_NAMES = { "generateModel" };
    
    public GenerateLatexTest(URI modelURI, File targetFolder,
            List<? extends Object> arguments) throws IOException {
        initialize(modelURI, targetFolder, arguments);
    }

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Arguments not valid : {model, folder}.");
            } else {
                URI modelURI = URI.createFileURI(args[0]);
                File folder = new File(args[1]);
                
                List<String> arguments = new ArrayList<String>();
                AbstractLatexHelper help1 = new AbstractLatexHelper() {
					@Override
					public void init() {
						prefs = new IPreferenceHelper() {
							
							@Override
							public String getPreferenceString(String p_constant) {
							if (p_constant.equals(PreferenceConstants.P_NAMESPACE)) {
								return "http://www.developnow.de/ecore2latex/2012";
							}
							if (p_constant.equals(PreferenceConstants.P_DESCRIPTION_KEY)) {
								return "description";
							}
							if (p_constant.equals(PreferenceConstants.P_SEMANTICS_KEY)) {
								return "semantics";
							}
							return null;
							}
						};
						
					}
				};
                LatexStaticHelper.setHelper(help1);
                
                GenerateLatexTest generator = new GenerateLatexTest(modelURI, folder, arguments);
                
                generator.doGenerate(new BasicMonitor());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getModuleName() {
        return MODULE_FILE_NAME;
    }
    
    @Override
    public String[] getTemplateNames() {
        return TEMPLATE_NAMES;
    }
    
}
