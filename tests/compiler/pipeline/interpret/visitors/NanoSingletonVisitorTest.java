/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NanoSingletonVisitorTest extends AbstractVisitorTest {

    private SingletonContext ctx;
    private NanoSingletonVisitor query;
    private ParseTree child;

    @Override
    public void init() {
        super.init();
        ctx = mock(SingletonContext.class);
        query = new NanoSingletonVisitor(master);
        when(ctx.getChildCount()).thenReturn(1);
    }

    @Test
    public void referenceCase() {
        doTest(ReferenceContext.class, ASTReferenceNode.class);
    }

    @Test
    public void operationCase() {
        doTest(OperationContext.class, ASTAssignmentNode.class);
    }

    @Test
    public void primitiveCase() {
        doTest(PrimitiveContext.class, ASTPrimitiveNode.class);
    }

    @Test
    public void assignmentCase() {
        doTest(AssignmentContext.class, ASTAssignmentNode.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalPayloadThrows() {
        configurePayload(ParserRuleContext.class);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroChildrenThrows() {
        configurePayload(AssignmentContext.class);
        when(ctx.getChildCount()).thenReturn(0);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void multipleChildrenThrows() {
        configurePayload(AssignmentContext.class);
        when(ctx.getChildCount()).thenReturn(2);
        query.visit(ctx);
    }

    private void configureChild(Class<? extends ParseTree> childClass,
                                Class<? extends ASTNode> resultClass) {

        configurePayload(childClass);
        ASTNode result = mock(resultClass);
        when(child.accept(master)).thenReturn(result);
    }

    private void configurePayload(Class<? extends ParseTree> childClass) {
        child = mock(childClass);
        when(child.getPayload()).thenReturn(child);
        when(ctx.getChild(0)).thenReturn(child);
    }

    private void doTest(Class<? extends ParseTree> childClass,
                        Class<? extends ASTNode> resultClass) {

        configureChild(childClass, resultClass);
        ASTNode expected = child.accept(master);
        ASTNode actual = query.visit(ctx);
        assertSame(expected, actual);
    }
}