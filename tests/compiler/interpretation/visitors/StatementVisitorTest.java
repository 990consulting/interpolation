/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.nodes.AssignmentNode;
import compiler.nodes.BaseNode;
import compiler.nodes.DefinitionNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatementVisitorTest extends AbstractVisitorTest {

    private StatementContext ctx;
    private StatementVisitor query;
    private ParseTree child;

    @Override
    public void init() {
        super.init();
        query = new StatementVisitor(master);

    }

    @Test
    public void assignmentCase() {
        doNormalTest(AssignmentContext.class, AssignmentNode.class);
    }

    @Test
    public void definitionCase() {
        doNormalTest(DefinitionContext.class, DefinitionNode.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalPayloadThrows() {
        buildContext(ParserRuleContext.class);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroChildrenThrows() {
        buildContext(AssignmentContext.class);
        when(ctx.getChildCount()).thenReturn(1);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void excessChildrenThrows() {
        buildContext(AssignmentContext.class);
        when(ctx.getChildCount()).thenReturn(3);
        query.visit(ctx);
    }

    private void doNormalTest(Class<? extends ParserRuleContext> childClass,
                                          Class<? extends BaseNode> resultClass) {

        configure(childClass, resultClass);
        BaseNode expected = ctx.getChild(0).accept(master);
        BaseNode actual = query.visit(ctx);
        assertSame(expected, actual);
    }

    private void buildContext(Class<? extends ParserRuleContext> childClass) {
        ctx = mock(StatementContext.class);
        when (ctx.getPayload()).thenReturn(ctx);
        child = mock(childClass);
        when(child.getPayload()).thenReturn(child);

        // Right token is the semicolon -- ignored
        when(ctx.getChildCount()).thenReturn(2);

        when(ctx.getChild(0)).thenReturn(child);

    }
    private void configure(Class<? extends ParserRuleContext> childClass,
                                          Class<? extends BaseNode> resultClass) {

        buildContext(childClass);
        BaseNode result = mock(resultClass);
        when(child.accept(master)).thenReturn(result);
    }
}