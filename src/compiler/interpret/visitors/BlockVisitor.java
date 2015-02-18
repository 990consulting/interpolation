/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nanosyntax.NanosyntaxParser;
import compiler.interpret.nodes.BlockNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class BlockVisitor extends AbstractBlockVisitor<BlockNode, NanosyntaxParser.BlockContext> {

    public BlockVisitor(NanoToASTVisitor master) {
        super(master, BlockNode::new);
    }

    @Override
    public BlockNode visit(NanosyntaxParser.BlockContext ctx) {
        if (ctx.getChildCount() < 2) {
            throw new IllegalArgumentException("Malformed block");
        }
        // Ignore the left and right brackets
        return doVisit(ctx, 1, ctx.getChildCount() - 1);
    }
}
