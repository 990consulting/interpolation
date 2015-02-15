/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.ValueNode;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;

/**
 * Created by dbborens on 2/14/15.
 */
public class SingletonVisitor extends AbstractNodeVisitor {
    private Class[] legalChildContexts = new Class[] {
            ReferenceContext.class,
            OperationContext.class,
            PrimitiveContext.class,
            AssignmentContext.class
    };

    public SingletonVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ValueNode visit(NanosyntaxParser.SingletonContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Unexpected child count");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, legalChildContexts);

        return (ValueNode) child.accept(master);
    }
}
