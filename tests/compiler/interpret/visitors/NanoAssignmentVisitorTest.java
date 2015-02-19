/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nanosyntax.NanosyntaxParser;
import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTBlockNode;
import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.ASTReferenceNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NanoAssignmentVisitorTest extends AbstractVisitorTest {

    private ParseTree first, middle, last;
    private NanosyntaxParser.AssignmentContext ctx;
    private NanoAssignmentVisitor query;

    @Override
    public void init() {
        super.init();
        query = new NanoAssignmentVisitor(master);
    }

    private void setupBlockCase() {
        first = makeParseTree(ASTReferenceNode.class);
        last = makeParseTree(ASTBlockNode.class);
        ctx = mock(NanosyntaxParser.AssignmentContext.class);
        when(ctx.getChild(0)).thenReturn(first);
        when(ctx.getChild(1)).thenReturn(last);
        when(ctx.getChildCount()).thenReturn(2);
    }

    private void setupSingletonCase() {
        first = makeParseTree(ASTReferenceNode.class);
        middle = mock(ParseTree.class);
        when(middle.getText()).thenReturn(":");
        last = makeParseTree(ASTBlockNode.class);
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
        ASTAssignmentNode result = query.visit(ctx);
        ASTNode expected = last.accept(master);
        ASTNode actual = result.getValue();
        assertSame(expected, actual);
    }

    private void doReferenceTest() throws Exception {
        ASTAssignmentNode result = query.visit(ctx);
        ASTNode expected = first.accept(master);
        ASTNode actual = result.getReference();
        assertSame(expected, actual);
    }

}