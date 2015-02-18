/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nanosyntax.NanosyntaxParser;
import compiler.interpret.nodes.RootNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class RootVisitor extends AbstractBlockVisitor<RootNode, NanosyntaxParser.RootContext> {

    public RootVisitor(NanoToASTVisitor master) {
        super(master, RootNode::new);
    }

    @Override
    public RootNode visit(NanosyntaxParser.RootContext ctx) {
        return doVisit(ctx, 0, ctx.getChildCount());
    }
}
