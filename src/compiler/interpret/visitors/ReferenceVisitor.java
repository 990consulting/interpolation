/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ReferenceNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.interpret.nanosyntax.NanosyntaxParser.ReferenceContext;

/**
 * Created by dbborens on 2/14/15.
 */
public class ReferenceVisitor extends AbstractNodeVisitor {
    public ReferenceVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ReferenceNode visit(ReferenceContext ctx) {
        if (ctx.getChildCount() == 1) {
            return leafCase(ctx);
        } else if (ctx.getChildCount() == 3) {
            return interiorCase(ctx);
        } else {
            throw new IllegalArgumentException("Unexpected node count");
        }
    }

    private ReferenceNode leafCase(ReferenceContext ctx) {
        String name = getName(ctx);
        return new ReferenceNode(name);
    }

    private ReferenceNode interiorCase(ReferenceContext ctx) {
        String name = getName(ctx);
        ParseTree rightChild = ctx.getChild(2);
        verifyPayload(rightChild, ReferenceContext.class);
        ReferenceNode childNode = (ReferenceNode) rightChild.accept(master);
        return new ReferenceNode(name, childNode);
    }

    private String getName(ReferenceContext ctx) {
        ParseTree child = ctx.getChild(0);
        verifyPayload(child, CommonToken.class);
        return child.getText();
    }
}
