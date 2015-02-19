/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.ASTValueNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NanoOperandVisitorTest extends AbstractVisitorTest {

    private OperandContext ctx;
    private ParserRuleContext child;
    private NanoOperandVisitor query;

    @Override
    public void init() {
        super.init();
        ctx = mock(OperandContext.class);
        child = mock(ParserRuleContext.class);
        query = new NanoOperandVisitor(master);
    }

    @Test
    public void referenceCase() throws Exception {
        doNormalTest(ReferenceContext.class);
    }

    @Test
    public void assignmentCase() throws Exception {
        doNormalTest(AssignmentContext.class);
    }

    @Test
    public void primitiveCase() throws Exception {
        doNormalTest(PrimitiveContext.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyPayloadThrows() throws Exception {
        baseConfig(null);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalChildThrows() throws Exception {
        ParserRuleContext payload = mock(ParserRuleContext.class);
        baseConfig(payload);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongChildCountThrows() throws Exception {
        ParserRuleContext payload = mock(ParserRuleContext.class);
        baseConfig(payload);
        when(ctx.getChildCount()).thenReturn(2);
    }

    private void baseConfig(ParserRuleContext payload) {
        when(child.getPayload()).thenReturn(payload);
        when(ctx.getChild(0)).thenReturn(child);
        when(ctx.getChildCount()).thenReturn(1);
        query.visit(ctx);
    }

    private void doNormalTest(Class<? extends ParserRuleContext> payloadClass)
            throws Exception {
        ParserRuleContext payload = mock(payloadClass);
        baseConfig(payload);

        ASTValueNode expected = mock(ASTValueNode.class);
        when(child.accept(master)).thenReturn(expected);
        ASTNode actual = query.visit(ctx);
        assertEquals(expected, actual);
    }

}