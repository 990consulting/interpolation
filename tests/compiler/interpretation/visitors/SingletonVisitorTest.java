/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.nodes.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingletonVisitorTest extends AbstractVisitorTest {

    private SingletonContext ctx;
    private SingletonVisitor query;
    private ParseTree child;

    @Override
    public void init() {
        super.init();
        ctx = mock(SingletonContext.class);
        query = new SingletonVisitor(master);
        when(ctx.getChildCount()).thenReturn(1);
    }

    @Test
    public void referenceCase() {
        doTest(ReferenceContext.class, ReferenceNode.class);
    }

    @Test
    public void operationCase() {
        doTest(OperationContext.class, OperationNode.class);
    }

    @Test
    public void primitiveCase() {
        doTest(PrimitiveContext.class, PrimitiveNode.class);
    }

    @Test
    public void assignmentCase() {
        doTest(AssignmentContext.class, AssignmentNode.class);
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
                                Class<? extends BaseNode> resultClass) {

        configurePayload(childClass);
        BaseNode result = mock(resultClass);
        when(child.accept(master)).thenReturn(result);
    }

    private void configurePayload(Class<? extends ParseTree> childClass) {
        child = mock(childClass);
        when(child.getPayload()).thenReturn(child);
        when(ctx.getChild(0)).thenReturn(child);
    }

    private void doTest(Class<? extends ParseTree> childClass,
                        Class<? extends BaseNode> resultClass) {

        configureChild(childClass, resultClass);
        BaseNode expected = child.accept(master);
        BaseNode actual = query.visit(ctx);
        assertSame(expected, actual);
    }
}