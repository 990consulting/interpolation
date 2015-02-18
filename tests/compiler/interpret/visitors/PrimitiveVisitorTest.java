/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.PrimitiveNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrimitiveVisitorTest extends AbstractVisitorTest {

    private PrimitiveContext ctx;
    private PrimitiveVisitor query;

    @Override
    public void init() {
        super.init();
        ctx = mock(PrimitiveContext.class);
        query = new PrimitiveVisitor(master);
        when(ctx.getChildCount()).thenReturn(1);
    }

    @Test
    public void narrowToString() {
        PrimitiveNode<String> expected = new PrimitiveNode<>("test");
        doNormalTest(expected, StringPrimitiveContext.class);
    }


    @Test
    public void narrowToInt() {
        PrimitiveNode<Integer> expected = new PrimitiveNode<>(1);
        doNormalTest(expected, IntPrimitiveContext.class);
    }

    @Test
    public void narrowToFloat() {
        PrimitiveNode<Double> expected = new PrimitiveNode<>(1.0);
        doNormalTest(expected, FloatPrimitiveContext.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongChildCountThrows() {
        when(ctx.getChildCount()).thenReturn(2);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongPayloadClassThrows() {
        configure(null, ParserRuleContext.class);
        query.visit(ctx);
    }

    private void configure(ASTNode expected, Class<? extends ParserRuleContext> payloadClass) {
        ParseTree child = mock(ParseTree.class);
        Object payload = mock(payloadClass);
        when(child.getPayload()).thenReturn(payload);
        when(child.accept(master)).thenReturn(expected);
        when(ctx.getChild(0)).thenReturn(child);
    }

    private void doNormalTest(ASTNode expected, Class<? extends ParserRuleContext> payloadClass) {
        configure(expected, payloadClass);
        ASTNode actual = query.visit(ctx);
        assertSame(expected, actual);
    }
}