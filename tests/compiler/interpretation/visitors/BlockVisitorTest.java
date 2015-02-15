/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.RootNode;
import compiler.nodes.StatementNode;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BlockVisitorTest extends AbstractVisitorTest {

    private BlockVisitor query;

    @Override
    public void init() {
        super.init();
        query = new BlockVisitor(master);
    }

    @Test
    public void visitReachesAllChildren() throws Exception {
        NanosyntaxParser.BlockContext ctx = (NanosyntaxParser.BlockContext) makeContext(NanosyntaxParser.BlockContext.class, StatementNode.class, 2);
        List<Object> expected = makeAcceptList(ctx);
        List<Object> actual = query
                .visit(ctx)
                .getChildren()
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongChildThrows() throws Exception {
        NanosyntaxParser.BlockContext ctx = (NanosyntaxParser.BlockContext) makeContext(NanosyntaxParser.BlockContext.class, RootNode.class, 2);
        query.visit(ctx);
    }
}