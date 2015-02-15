/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;
public class NanoToASTVisitorTest {

    private NanoToASTVisitor query;
    private SlaveVisitorManager manager;

    @Before
    public void init() throws Exception {
        manager = mock(SlaveVisitorManager.class);
        query = new NanoToASTVisitor(manager);
    }

    @Test
    public void testVisitRoot() throws Exception {
        RootContext ctx = mock(RootContext.class);
        RootNode expected = mock(RootNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitRoot(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitStatement() throws Exception {
        StatementContext ctx = mock(StatementContext.class);
        StatementNode expected = mock(StatementNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitStatement(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitAssignment() throws Exception {
        AssignmentContext ctx = mock(AssignmentContext.class);
        AssignmentNode expected = mock(AssignmentNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitAssignment(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitBlock() throws Exception {
        BlockContext ctx = mock(BlockContext.class);
        BlockNode expected = mock(BlockNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitBlock(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitSingleton() throws Exception {
        SingletonContext ctx = mock(SingletonContext.class);
        ValueNode expected = mock(ValueNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitSingleton(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitOperation() throws Exception {
        OperationContext ctx = mock(OperationContext.class);
        OperationNode expected = mock(OperationNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitOperation(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitOperand() throws Exception {
        OperandContext ctx = mock(OperandContext.class);
        ValueNode expected = mock(ValueNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitOperand(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitOperator() throws Exception {
        OperatorContext ctx = mock(OperatorContext.class);
        PrimitiveNode<String> expected = (PrimitiveNode<String>) mock(PrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitOperator(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitReference() throws Exception {
        ReferenceContext ctx = mock(ReferenceContext.class);
        ReferenceNode expected = mock(ReferenceNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitReference(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitPrimitive() throws Exception {
        PrimitiveContext ctx = mock(PrimitiveContext.class);
        PrimitiveNode expected = mock(PrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitPrimitive(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitStringPrimitive() throws Exception {
        StringPrimitiveContext ctx = mock(StringPrimitiveContext.class);
        PrimitiveNode<String> expected = (PrimitiveNode<String>) mock(PrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitStringPrimitive(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitFloatPrimitive() throws Exception {
        FloatPrimitiveContext ctx = mock(FloatPrimitiveContext.class);
        PrimitiveNode<Double> expected = (PrimitiveNode<Double>) mock(PrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitFloatPrimitive(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitIntPrimitive() throws Exception {
        IntPrimitiveContext ctx = mock(IntPrimitiveContext.class);
        PrimitiveNode<Integer> expected = (PrimitiveNode<Integer>) mock(PrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        BaseNode actual = query.visitIntPrimitive(ctx);
        assertSame(actual, expected);
    }

}