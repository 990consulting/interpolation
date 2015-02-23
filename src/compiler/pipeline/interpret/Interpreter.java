/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret;

import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.nodes.ASTRootNode;
import compiler.pipeline.interpret.visitors.NanosyntaxAntlrBinding;

import java.io.File;

/**
 * Created by dbborens on 2/13/15.
 */
public class Interpreter {

    private AntlrBinding<ASTNode> antlr;

    public Interpreter() {
        this(new NanosyntaxAntlrBinding());
    }

    public Interpreter(AntlrBinding<ASTNode> antlr) {
        this.antlr = antlr;
    }

    public ASTRootNode interpret(File file) {
        verify(file);
        return (ASTRootNode) antlr.interpret(file);
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
