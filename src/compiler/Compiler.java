/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler;

import compiler.pipeline.initialize.InitializationWalker;
import compiler.pipeline.interpret.Interpreter;
import compiler.pipeline.interpret.nodes.ASTRootNode;
import compiler.symbol.tables.RootSymbolTable;
import compiler.util.UserError;

import java.io.File;

/**
 * Created by dbborens on 2/16/15.
 */
public class Compiler {

    // TODO the compiler really should return a runner object.
    public void compile(File source) throws UserError {
        // Stage 1: @compiler.pipeline.interpret
        // Interpret nanosyntax to abstract syntax tree.
        Interpreter interpreter = new Interpreter();
        ASTRootNode astRoot = interpreter.interpret(source);

        // Stage 2: @compiler.pipeline.initialize
        // Initialize symbol tables.
        InitializationWalker initWalker = new InitializationWalker();
        RootSymbolTable rootSymbolTable = initWalker.walk(astRoot);

        // Stage 3: @compiler.pipeline.translate
        // Translate abstract syntax tree into an approximately one-to-one
        // representation of the Java objects that will be instantiated and
        // linked in order to run the model.
//        Translator translator = new Translator(rootSymbolTable);
//        TranslatedNode objectTree = translator.translate(astRoot);

        // Stage 4: @compiler.pipeline.infer
        // Infer defaults for any members not specified by the user, or fail
        // if this is not possible.

        // Stage 5: @compiler.pipeline.assemble
        // Construct Java objects in memory by traversing the tree.

        // Return the constructed runner object (the root of the simulation).
    }
}
