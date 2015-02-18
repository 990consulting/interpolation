/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nanosyntax.NanosyntaxParser;
import compiler.interpret.nodes.ValueNode;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by dbborens on 2/14/15.
 */
public class OperandVisitor extends AbstractNodeVisitor {

    private final Class[] legalChildClasses = new Class[] {
        NanosyntaxParser.ReferenceContext.class,
        NanosyntaxParser.AssignmentContext.class,
        NanosyntaxParser.PrimitiveContext.class
    };

    public OperandVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ValueNode visit(NanosyntaxParser.OperandContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Unexpected number of children in operand");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, legalChildClasses);
        return (ValueNode) child.accept(master);
    }

}
