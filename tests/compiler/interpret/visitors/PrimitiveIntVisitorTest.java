/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.PrimitiveNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static compiler.interpret.nanosyntax.NanosyntaxParser.IntPrimitiveContext;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrimitiveIntVisitorTest extends AbstractVisitorTest {

    private IntPrimitiveContext ctx;
    private PrimitiveIntVisitor query;
    private ParseTree child;

    @Override
    public void init() {
        super.init();

        ctx = mock(IntPrimitiveContext.class);
        when(ctx.getChildCount()).thenReturn(1);
        child = mock(ParseTree.class);
        Object payload = mock(CommonToken.class);
        when(child.getPayload()).thenReturn(payload);
        when(ctx.getChild(0)).thenReturn(child);
        query = new PrimitiveIntVisitor(master);
    }

    @Test
    public void ordinaryCase() {
        doNormalTest("1");
    }

    @Test
    public void negativeCase() {
        doNormalTest("-1");
    }

    private void doNormalTest(String valueString) {
        when(child.getText()).thenReturn(valueString);
        Integer expected = Integer.valueOf(valueString);

        PrimitiveNode<Integer> result = query.visit(ctx);
        Integer actual = result.getContent();

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void payloadNotTokenThrows() {
        Object payload = mock(ParserRuleContext.class);
        when(child.getPayload()).thenReturn(payload);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongChildCountThrows() {
        when(ctx.getChildCount()).thenReturn(2);
        query.visit(ctx);
    }
}