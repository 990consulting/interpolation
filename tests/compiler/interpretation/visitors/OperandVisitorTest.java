/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.nodes.BaseNode;
import compiler.nodes.ValueNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;

public class OperandVisitorTest extends AbstractVisitorTest {

    private OperandContext ctx;
    private ParserRuleContext child;
    private OperandVisitor query;

    @Override
    public void init() {
        super.init();
        ctx = mock(OperandContext.class);
        child = mock(ParserRuleContext.class);
        query = new OperandVisitor(master);
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

        ValueNode expected = mock(ValueNode.class);
        when(child.accept(master)).thenReturn(expected);
        BaseNode actual = query.visit(ctx);
        assertEquals(expected, actual);
    }

}