/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation;

import compiler.nodes.BaseNode;

import java.io.File;

/**
 * Created by dbborens on 2/13/15.
 */
public class Interpreter {

    private AntlrBinding<BaseNode> antlr;

    public Interpreter(AntlrBinding<BaseNode> antlr) {
        this.antlr = antlr;
    }

    public BaseNode interpret(File file) {
        verify(file);
        return antlr.interpret(file);
    }

    private void verify(File file) {
        if (file == null) {
            throw new IllegalArgumentException("No project file specified.");
        }

        if (!file.exists()) {
            throw new IllegalArgumentException("Project file not found: " + file.getAbsolutePath());
        }
    }
}
