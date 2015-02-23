/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxLexer;
import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nanosyntax.NanosyntaxVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An interface to ANTLR4 boilerplate. This class is not
 * tested because the business logic is from ANTLR, which
 * has its own tests.
 *
 * Created by dbborens on 2/13/15.
 */
public class AntlrBinding<T> {

    private NanosyntaxVisitor<T> visitor;

    public AntlrBinding(NanosyntaxVisitor<T> visitor) {
        this.visitor = visitor;
    }

    /**
     * Read a file, lex it and parse it, visit the tree, and return
     * the root in the form returned by the visitor.
     *
     * @param file
     * @return
     */
    public T interpret(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ANTLRInputStream input = new ANTLRInputStream(inputStream);
            NanosyntaxLexer lexer = new NanosyntaxLexer(input);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            NanosyntaxParser parser = new NanosyntaxParser(tokenStream);
            ParseTree tree = parser.root();
            T root = visitor.visit(tree);
            return root;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File not found: " + file.getAbsolutePath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
