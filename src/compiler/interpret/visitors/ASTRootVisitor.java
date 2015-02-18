/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nanosyntax.NanosyntaxParser;
import compiler.interpret.nodes.ASTRootNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class ASTRootVisitor extends AbstractASTBlockVisitor<ASTRootNode, NanosyntaxParser.RootContext> {

    public ASTRootVisitor(NanoToASTVisitor master) {
        super(master, ASTRootNode::new);
    }

    @Override
    public ASTRootNode visit(NanosyntaxParser.RootContext ctx) {
        return doVisit(ctx, 0, ctx.getChildCount());
    }
}
