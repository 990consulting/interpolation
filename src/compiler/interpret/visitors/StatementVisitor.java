/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.StatementNode;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;

/**
 * Created by dbborens on 2/14/15.
 */
public class StatementVisitor extends AbstractNodeVisitor {
    private Class[] legalChildContexts = new Class[] {
            AssignmentContext.class,
            DefinitionContext.class
    };

    public StatementVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public StatementNode visit(StatementContext ctx) {
        // Second child is a semicolon (checked by ANTLR -- ignored)
        if (ctx.getChildCount() != 2) {
            throw new IllegalArgumentException("Unexpected child count");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, legalChildContexts);

        return (StatementNode) child.accept(master);
    }

}
