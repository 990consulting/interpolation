/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nodes.ASTRootNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class NanoRootVisitor extends AbstractNanoBlockVisitor<ASTRootNode, NanosyntaxParser.RootContext> {

    public NanoRootVisitor(NanoToASTVisitor master) {
        super(master, ASTRootNode::new);
    }

    @Override
    public ASTRootNode visit(NanosyntaxParser.RootContext ctx) {
        return doVisit(ctx, 0, ctx.getChildCount());
    }
}
