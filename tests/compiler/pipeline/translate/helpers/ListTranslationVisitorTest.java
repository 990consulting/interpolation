/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.util.SyntaxError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class ListTranslationVisitorTest {

    private ListTranslationVisitor query;
    private ListValueLoader loader;

    @Before
    public void init() throws Exception {
        loader = mock(ListValueLoader.class);
        query = new ListTranslationVisitor();
    }

    @Test
    public void returnsLoaderOutput() throws Exception {
        ASTReferenceNode root = mock(ASTReferenceNode.class);
        ListObjectNode expected = mock(ListObjectNode.class);
        when(loader.finish()).thenReturn(expected);
        ListObjectNode actual = query.translate(root, loader);
        assertSame(expected, actual);
    }

    @Test
    public void referenceLoadsNothing() throws Exception {
        ASTReferenceNode root = mock(ASTReferenceNode.class);
        ListObjectNode expected = mock(ListObjectNode.class);
        when(loader.finish()).thenReturn(expected);
        query.translate(root, loader);
        verify(loader).finish();
        verify(loader, never()).loadAssignment(any());
    }

    @Test
    public void assignmentLoadsChild() throws Exception {
        ASTValueNode child = mock(ASTValueNode.class);
        ASTReferenceNode ref = mock(ASTReferenceNode.class);
        ASTAssignmentNode root = new ASTAssignmentNode(ref, child);
        query.translate(root, loader);
        verify(loader).loadAssignment(child);
    }

    @Test
    public void blockLoadsChildren() throws Exception {
        ASTBlockNode root = mock(ASTBlockNode.class);
        ASTValueNode child1 = mock(ASTValueNode.class);
        ASTValueNode child2 = mock(ASTValueNode.class);
        Stream<ASTValueNode> childStream = Stream.of(child1, child2);
        when(root.getChildren()).thenReturn(childStream);

        query.translate(root, loader);

        InOrder inOrder = inOrder(loader);
        inOrder.verify(loader).loadAssignment(child1);
        inOrder.verify(loader).loadAssignment(child2);
        inOrder.verify(loader).finish();
        inOrder.verifyNoMoreInteractions();
    }

    @Test(expected = SyntaxError.class)
    public void unrecognizedInputThrows() throws Exception {
        ASTValueNode root = mock(ASTValueNode.class);
        query.translate(root, loader);
    }
}