/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.ReferenceContext;

/**
 * Created by dbborens on 2/14/15.
 */
public class NanoReferenceVisitor extends AbstractNanoNodeVisitor {
    public NanoReferenceVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTReferenceNode visit(ReferenceContext ctx) {
        if (ctx.getChildCount() == 1) {
            return leafCase(ctx);
        } else if (ctx.getChildCount() == 3) {
            return interiorCase(ctx);
        } else {
            throw new IllegalArgumentException("Unexpected node count");
        }
    }

    private ASTReferenceNode leafCase(ReferenceContext ctx) {
        String name = getName(ctx);
        return new ASTReferenceNode(name);
    }

    private ASTReferenceNode interiorCase(ReferenceContext ctx) {
        String name = getName(ctx);
        ParseTree rightChild = ctx.getChild(2);
        verifyPayload(rightChild, ReferenceContext.class);
        ASTReferenceNode childNode = (ASTReferenceNode) rightChild.accept(master);
        return new ASTReferenceNode(name, childNode);
    }

    private String getName(ReferenceContext ctx) {
        ParseTree child = ctx.getChild(0);
        verifyPayload(child, CommonToken.class);
        return child.getText();
    }
}
