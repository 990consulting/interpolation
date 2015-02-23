/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.ReferenceContext;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NanoReferenceVisitorTest extends AbstractVisitorTest {

    private ReferenceContext ctx;
    private NanoReferenceVisitor query;
    private String rootName;
    @Override
    public void init() {
        super.init();
        rootName = "first";
        query = new NanoReferenceVisitor(master);
    }

    private ReferenceContext buildContext(String name, boolean leaf) {
        ReferenceContext ret = mock(ReferenceContext.class);
        when (ret.getPayload()).thenReturn(ret);
        ParseTree left = mock(ParseTree.class);
        Object payload = mock(CommonToken.class);
        when(left.getPayload()).thenReturn(payload);
        when(left.getText()).thenReturn(name);
        when(ret.getChildCount()).thenReturn(leaf? 1 : 3);
        when(ret.getChild(0)).thenReturn(left);
        return ret;
    }

    @Test
    public void leafCase() {
        ctx = buildContext(rootName, true);
        ASTReferenceNode actual = query.visit(ctx);
        checkReferenceNode(actual, rootName, true);
    }


    @Test
    public void interiorCase() {
        ctx = buildContext(rootName, false);
        ReferenceContext childMock = buildContext("second", true);
        ASTReferenceNode subNode = mock(ASTReferenceNode.class);
        when(childMock.accept(master)).thenReturn(subNode);
        when(ctx.getChild(2)).thenReturn(childMock);

        ASTReferenceNode actual = query.visit(ctx);

        checkReferenceNode(actual, rootName, false);
        assertSame(subNode, actual.getChild());
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroChildrenThrows() {
        ctx = buildContext(rootName, true);
        when(ctx.getChildCount()).thenReturn(0);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void multipleChildrenThrows() {
        ctx = buildContext(rootName, true);
        when(ctx.getChildCount()).thenReturn(4);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonReferenceRightPayloadThrows() {
        ctx = buildContext(rootName, false);
        ParserRuleContext child = mock(ParserRuleContext.class);
        when(child.getPayload()).thenReturn(child);
        when(ctx.getChild(2)).thenReturn(child);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonTokenLeftPayloadThrows() {
        ctx = buildContext(rootName, true);
        ParserRuleContext child = mock(ParserRuleContext.class);
        when(child.getPayload()).thenReturn(child);
        when(ctx.getChild(0)).thenReturn(child);
        query.visit(ctx);
    }

    private void checkReferenceNode(ASTReferenceNode actual, String name, boolean leaf) {
        assertEquals(name, actual.getIdentifier());

        if (leaf) {
            assertFalse(actual.hasChild());
            assertNull(actual.getChild());
        } else {
            assertTrue(actual.hasChild());
            assertNotNull(actual.getChild());
        }
    }
}