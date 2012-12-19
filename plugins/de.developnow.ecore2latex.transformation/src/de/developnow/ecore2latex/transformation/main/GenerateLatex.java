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
package de.developnow.ecore2latex.transformation.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;
import org.eclipse.emf.common.util.URI;

public class GenerateLatex extends AbstractAcceleoGenerator {
    public static final String MODULE_FILE_NAME = "/de/developnow/ecore2latex/transformation/main/generateLatex";
    
    public static final String[] TEMPLATE_NAMES = { "generateModel" };
    
    public GenerateLatex(URI modelURI, File targetFolder,
            List<? extends Object> arguments) throws IOException {
        initialize(modelURI, targetFolder, arguments);
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
