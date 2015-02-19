/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nanosyntax.NanosyntaxParser;
import compiler.interpret.nodes.ASTValueNode;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by dbborens on 2/14/15.
 */
public class NanoOperandVisitor extends AbstractNanoNodeVisitor {

    private final Class[] legalChildClasses = new Class[] {
        NanosyntaxParser.ReferenceContext.class,
        NanosyntaxParser.AssignmentContext.class,
        NanosyntaxParser.PrimitiveContext.class
    };

    public NanoOperandVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTValueNode visit(NanosyntaxParser.OperandContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Unexpected number of children in operand");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, legalChildClasses);
        return (ASTValueNode) child.accept(master);
    }

}
