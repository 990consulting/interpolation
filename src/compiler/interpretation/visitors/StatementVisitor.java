/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.StatementNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class StatementVisitor extends NodeVisitor {
    public StatementVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public StatementNode visit(NanosyntaxParser.StatementContext ctx) {
        return null;
    }
}
