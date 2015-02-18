/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ASTPrimitiveNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static compiler.interpret.nanosyntax.NanosyntaxParser.OperatorContext;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ASTOperatorVisitorTest extends AbstractVisitorTest {

    private ASTOperatorVisitor query;
    private OperatorContext ctx;
    private ParseTree child;
    private Object payload;

    @Override
    public void init() {
        super.init();
        ctx = mock(OperatorContext.class);
        when(ctx.getChildCount()).thenReturn(1);
        child = mock(ParseTree.class);
        payload = mock(CommonToken.class);
        when(child.getPayload()).thenReturn(payload);
        when(child.getText()).thenReturn(">=");
        when(ctx.getChild(0)).thenReturn(child);
        query = new ASTOperatorVisitor(master);
    }

    @Test
    public void visitGetsTokenValue() throws Exception {
        ASTPrimitiveNode<String> result = query.visit(ctx);

        String expected = ">=";
        String actual = result.getContent();

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void payloadNotTokenThrows() throws Exception {
        payload = mock(Object.class);
        when(child.getPayload()).thenReturn(payload);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongChildCountThrows() throws Exception {
        when(ctx.getChildCount()).thenReturn(0);
        query.visit(ctx);
    }

}