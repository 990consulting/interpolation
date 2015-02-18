/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret;

import compiler.interpret.nodes.ASTNode;

import java.io.File;

/**
 * Created by dbborens on 2/13/15.
 */
public class Interpreter {

    private AntlrBinding<ASTNode> antlr;

    public Interpreter(AntlrBinding<ASTNode> antlr) {
        this.antlr = antlr;
    }

    public ASTNode interpret(File file) {
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
