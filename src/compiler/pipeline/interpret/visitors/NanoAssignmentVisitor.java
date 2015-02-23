/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.AssignmentContext;

/**
 * Created by dbborens on 2/14/15.
 */
public class NanoAssignmentVisitor extends AbstractNanoNodeVisitor {

    public NanoAssignmentVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTAssignmentNode visit(AssignmentContext ctx) {
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

    private ASTAssignmentNode singletonCase(AssignmentContext ctx) {
        ASTAssignmentNode ret = resolve(ctx, 0, 2);
        return ret;
    }

    private ASTAssignmentNode resolve(AssignmentContext ctx, int refChild, int valueChild) {
        ASTReferenceNode reference = (ASTReferenceNode) ctx.getChild(refChild).accept(master);
        ASTValueNode value = (ASTValueNode) ctx.getChild(valueChild).accept(master);

        return new ASTAssignmentNode(reference, value);
    }

    private ASTAssignmentNode blockCase(AssignmentContext ctx) {
        ASTAssignmentNode ret = resolve(ctx, 0, 1);
        return ret;
    }
}
