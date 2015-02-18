/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.*;
import org.junit.Before;
import org.junit.Test;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class NanoToASTVisitorTest {

    private NanoToASTVisitor query;
    private SlaveNanoVisitorManager manager;

    @Before
    public void init() throws Exception {
        manager = mock(SlaveNanoVisitorManager.class);
        query = new NanoToASTVisitor(manager);
    }

    @Test
    public void testVisitRoot() throws Exception {
        RootContext ctx = mock(RootContext.class);
        ASTRootNode expected = mock(ASTRootNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitRoot(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitStatement() throws Exception {
        StatementContext ctx = mock(StatementContext.class);
        ASTStatementNode expected = mock(ASTStatementNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitStatement(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitAssignment() throws Exception {
        AssignmentContext ctx = mock(AssignmentContext.class);
        ASTAssignmentNode expected = mock(ASTAssignmentNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitAssignment(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitBlock() throws Exception {
        BlockContext ctx = mock(BlockContext.class);
        ASTBlockNode expected = mock(ASTBlockNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitBlock(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitSingleton() throws Exception {
        SingletonContext ctx = mock(SingletonContext.class);
        ASTValueNode expected = mock(ASTValueNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitSingleton(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitOperation() throws Exception {
        OperationContext ctx = mock(OperationContext.class);
        ASTOperationNode expected = mock(ASTOperationNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitOperation(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitOperand() throws Exception {
        OperandContext ctx = mock(OperandContext.class);
        ASTValueNode expected = mock(ASTValueNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitOperand(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitOperator() throws Exception {
        OperatorContext ctx = mock(OperatorContext.class);
        ASTPrimitiveNode<String> expected = (ASTPrimitiveNode<String>) mock(ASTPrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitOperator(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitReference() throws Exception {
        ReferenceContext ctx = mock(ReferenceContext.class);
        ASTReferenceNode expected = mock(ASTReferenceNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitReference(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitPrimitive() throws Exception {
        PrimitiveContext ctx = mock(PrimitiveContext.class);
        ASTPrimitiveNode expected = mock(ASTPrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitPrimitive(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitStringPrimitive() throws Exception {
        StringPrimitiveContext ctx = mock(StringPrimitiveContext.class);
        ASTPrimitiveNode<String> expected = (ASTPrimitiveNode<String>) mock(ASTPrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitStringPrimitive(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitFloatPrimitive() throws Exception {
        FloatPrimitiveContext ctx = mock(FloatPrimitiveContext.class);
        ASTPrimitiveNode<Double> expected = (ASTPrimitiveNode<Double>) mock(ASTPrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitFloatPrimitive(ctx);
        assertSame(actual, expected);
    }

    @Test
    public void testVisitIntPrimitive() throws Exception {
        IntPrimitiveContext ctx = mock(IntPrimitiveContext.class);
        ASTPrimitiveNode<Integer> expected = (ASTPrimitiveNode<Integer>) mock(ASTPrimitiveNode.class);
        when(manager.visit(ctx)).thenReturn(expected);

        ASTNode actual = query.visitIntPrimitive(ctx);
        assertSame(actual, expected);
    }

}