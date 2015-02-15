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

import static compiler.interpretation.nanosyntax.NanosyntaxParser.StringPrimitiveContext;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrimitiveStringVisitorTest extends AbstractVisitorTest {

    private StringPrimitiveContext ctx;
    private PrimitiveStringVisitor query;
    private ParseTree child;

    @Override
    public void init() {
        super.init();

        ctx = mock(StringPrimitiveContext.class);
        when(ctx.getChildCount()).thenReturn(1);
        child = mock(ParseTree.class);
        Object payload = mock(CommonToken.class);
        when(child.getPayload()).thenReturn(payload);
        when(ctx.getChild(0)).thenReturn(child);
        query = new PrimitiveStringVisitor(master);
    }

    @Test
    public void quotedCase() {
        doNormalTest("\"test\"");
    }

    @Test
    public void unquotedCase() {
        doNormalTest("test");
    }

    private void doNormalTest(String valueString) {
        when(child.getText()).thenReturn(valueString);
        String expected = "test";
        PrimitiveNode<String> result = query.visit(ctx);
        String actual = result.getContent();

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