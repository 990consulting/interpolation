/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.nodes.PrimitiveNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.FloatPrimitiveContext;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrimitiveFloatVisitorTest extends AbstractVisitorTest {

    private FloatPrimitiveContext ctx;
    private PrimitiveFloatVisitor query;
    private ParseTree child;

    @Override
    public void init() {
        super.init();

        ctx = mock(FloatPrimitiveContext.class);
        when(ctx.getChildCount()).thenReturn(1);
        child = mock(ParseTree.class);
        Object payload = mock(CommonToken.class);
        when(child.getPayload()).thenReturn(payload);
        when(ctx.getChild(0)).thenReturn(child);
        query = new PrimitiveFloatVisitor(master);
    }

    @Test
    public void ordinaryCase() {
        doNormalTest("0.1");
    }

    @Test
    public void scientificCase() {
        doNormalTest("1e-2");
    }

    @Test
    public void negativeCase() {
        doNormalTest("-0.1");
    }

    private void doNormalTest(String valueString) {
        when(child.getText()).thenReturn(valueString);
        Double expected = Double.valueOf(valueString);

        PrimitiveNode<Double> result = query.visit(ctx);
        Double actual = result.getContent();

        assertEquals(expected, actual, epsilon());
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