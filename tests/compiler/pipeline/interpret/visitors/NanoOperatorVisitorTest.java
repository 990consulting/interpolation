/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.OperatorContext;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class NanoOperatorVisitorTest {

    private OperatorContext ctx;
    private ParseTree child;
    private NanoOperatorVisitor query;

    @Before
    public void init() throws Exception {
        ctx = mock(OperatorContext.class);
        child = mock(ParseTree.class);
        when(child.getText()).thenReturn("+");
        when(child.getPayload()).thenReturn(mock(CommonToken.class));
        when(ctx.getChildCount()).thenReturn(1);
        when(ctx.getChild(0)).thenReturn(child);

        query = new NanoOperatorVisitor(null);
    }

    public void visitCreatesReferenceNode() throws Exception {
        ASTReferenceNode expected = new ASTReferenceNode("+");
        ASTReferenceNode actual = query.visit(ctx);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalPayloadThrows() throws Exception {
        when(child.getPayload()).thenReturn(mock(Object.class));
        when(ctx.getChild(0)).thenReturn(child);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooManyChildrenThrows() throws Exception {
        when(ctx.getChildCount()).thenReturn(2);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroChildrenThrows() throws Exception {
        when(ctx.getChildCount()).thenReturn(0);
        query.visit(ctx);
    }

}