/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.OperatorContext;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ASTOperatorVisitorTest extends AbstractVisitorTest {

    private NanoOperatorVisitor query;
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
        query = new NanoOperatorVisitor(master);
    }

    public void doNothing() {
        fail();
    }
//    @Test
//    public void visitGetsTokenValue() throws Exception {
//        ASTPrimitiveNode<String> result = query.visit(ctx);
//
//        String expected = ">=";
//        String actual = result.getContent();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void payloadNotTokenThrows() throws Exception {
//        payload = mock(Object.class);
//        when(child.getPayload()).thenReturn(payload);
//        query.visit(ctx);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void wrongChildCountThrows() throws Exception {
//        when(ctx.getChildCount()).thenReturn(0);
//        query.visit(ctx);
//    }

}