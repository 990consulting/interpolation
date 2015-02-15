/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.BlockNode;
import compiler.nodes.RootNode;
import org.mockito.cglib.core.Block;

/**
 * Created by dbborens on 2/14/15.
 */
public class BlockVisitor extends AbstractBlockVisitor<BlockNode, NanosyntaxParser.BlockContext> {

    public BlockVisitor(NanoToASTVisitor master) {
        super(master, BlockNode::new);
    }
}
