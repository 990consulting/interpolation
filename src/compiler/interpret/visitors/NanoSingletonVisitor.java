/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nanosyntax.NanosyntaxParser;
import compiler.interpret.nodes.ASTValueNode;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;

/**
 * Created by dbborens on 2/14/15.
 */
public class NanoSingletonVisitor extends AbstractNanoNodeVisitor {
    private Class[] legalChildContexts = new Class[] {
            ReferenceContext.class,
            OperationContext.class,
            PrimitiveContext.class,
            AssignmentContext.class
    };

    public NanoSingletonVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTValueNode visit(NanosyntaxParser.SingletonContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Unexpected child count");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, legalChildContexts);

        return (ASTValueNode) child.accept(master);
    }
}
