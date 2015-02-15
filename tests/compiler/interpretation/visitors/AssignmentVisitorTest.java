/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.AssignmentNode;
import compiler.nodes.BaseNode;
import compiler.nodes.BlockNode;
import compiler.nodes.ReferenceNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AssignmentVisitorTest extends AbstractVisitorTest {

    private ParseTree first, middle, last;
    private NanosyntaxParser.AssignmentContext ctx;
    private AssignmentVisitor query;

    @Override
    public void init() {
        super.init();
        query = new AssignmentVisitor(master);
    }

    private void setupBlockCase() {
        first = makeParseTree(ReferenceNode.class);
        last = makeParseTree(BlockNode.class);
        ctx = mock(NanosyntaxParser.AssignmentContext.class);
        when(ctx.getChild(0)).thenReturn(first);
        when(ctx.getChild(1)).thenReturn(last);
        when(ctx.getChildCount()).thenReturn(2);
    }

    private void setupSingletonCase() {
        first = makeParseTree(ReferenceNode.class);
        middle = mock(ParseTree.class);
        when(middle.getText()).thenReturn(":");
        last = makeParseTree(BlockNode.class);
        ctx = mock(NanosyntaxParser.AssignmentContext.class);
        when(ctx.getChild(0)).thenReturn(first);
        when(ctx.getChild(1)).thenReturn(middle);
        when(ctx.getChild(2)).thenReturn(last);
        when(ctx.getChildCount()).thenReturn(3);
    }

    @Test
    public void blockCaseReferenceVisited() throws Exception {
        setupBlockCase();
        doReferenceTest();
    }

    @Test
    public void blockCaseValueVisited() throws Exception {
        setupBlockCase();
        doValueTest();
    }

    @Test
    public void singletonCaseReferenceVisited() throws Exception {
        setupSingletonCase();
        doReferenceTest();
    }

    @Test
    public void singletonCaseValueVisited() throws Exception {
        setupSingletonCase();
        doValueTest();
    }

    private void doValueTest() throws Exception {
        AssignmentNode result = query.visit(ctx);
        BaseNode expected = last.accept(master);
        BaseNode actual = result.getValue();
        assertSame(expected, actual);
    }

    private void doReferenceTest() throws Exception {
        AssignmentNode result = query.visit(ctx);
        BaseNode expected = first.accept(master);
        BaseNode actual = result.getReference();
        assertSame(expected, actual);
    }

}