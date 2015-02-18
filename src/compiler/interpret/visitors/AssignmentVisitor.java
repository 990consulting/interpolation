/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.AssignmentNode;
import compiler.interpret.nodes.ReferenceNode;
import compiler.interpret.nodes.ValueNode;

import static compiler.interpret.nanosyntax.NanosyntaxParser.AssignmentContext;

/**
 * Created by dbborens on 2/14/15.
 */
public class AssignmentVisitor extends AbstractNodeVisitor {

    public AssignmentVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public AssignmentNode visit(AssignmentContext ctx) {
        int n = ctx.getChildCount();

        // myRef {...}
        if (n == 2) {
            return blockCase(ctx);

            // myRef: myValue
        } else if (n == 3) {
            return singletonCase(ctx);

        } else {
            throw new IllegalStateException("Unexpected node count in AssignmentNode");
        }
    }

    private AssignmentNode singletonCase(AssignmentContext ctx) {
        AssignmentNode ret = resolve(ctx, 0, 2);
        return ret;
    }

    private AssignmentNode resolve(AssignmentContext ctx, int refChild, int valueChild) {
        ReferenceNode reference = (ReferenceNode) ctx.getChild(refChild).accept(master);
        ValueNode value = (ValueNode) ctx.getChild(valueChild).accept(master);

        return new AssignmentNode(reference, value);
    }

    private AssignmentNode blockCase(AssignmentContext ctx) {
        AssignmentNode ret = resolve(ctx, 0, 1);
        return ret;
    }
}
