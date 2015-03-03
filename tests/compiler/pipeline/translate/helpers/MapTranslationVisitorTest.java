/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.util.SyntaxError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class MapTranslationVisitorTest {

    private MapTranslationVisitor query;
    private MapAssignmentLoader loader;

    @Before
    public void init() throws Exception {
        loader = mock(MapAssignmentLoader.class);
        query = new MapTranslationVisitor();
    }

    @Test
    public void returnsLoaderOutput() throws Exception {
        ASTReferenceNode root = mock(ASTReferenceNode.class);
        MapObjectNode expected = mock(MapObjectNode.class);
        when(loader.finish()).thenReturn(expected);
        MapObjectNode actual = query.translate(root, loader);
        assertSame(expected, actual);
    }

    @Test
    public void referenceLoadsNothing() throws Exception {
        ASTReferenceNode root = mock(ASTReferenceNode.class);
        MapObjectNode expected = mock(MapObjectNode.class);
        when(loader.finish()).thenReturn(expected);
        query.translate(root, loader);
        verify(loader).finish();
        verify(loader, never()).loadAssignment(any());
    }

    @Test
    public void assignmentLoadsChild() throws Exception {
        ASTAssignmentNode root = mock(ASTAssignmentNode.class);
        query.translate(root, loader);
        verify(loader).loadAssignment(root);
    }

    @Test
    public void blockLoadsChildren() throws Exception {
        ASTBlockNode root = mock(ASTBlockNode.class);
        ASTAssignmentNode child1 = mock(ASTAssignmentNode.class);
        ASTAssignmentNode child2 = mock(ASTAssignmentNode.class);
        Stream<ASTValueNode> childStream = Stream.of(child1, child2);
        when(root.getChildren()).thenReturn(childStream);

        query.translate(root, loader);

        InOrder inOrder = inOrder(loader);
        inOrder.verify(loader).loadAssignment(child1);
        inOrder.verify(loader).loadAssignment(child2);
        inOrder.verify(loader).finish();
        inOrder.verifyNoMoreInteractions();
    }

    /**
     * In the context of map nodes, a block is just a way of conveying
     * multiple assignments (or definitions). Anything else should throw--
     * including a nested block.
     *
     * @throws Exception
     */
    @Test(expected = SyntaxError.class)
    public void blockWithIllegalChildrenThrows() throws Exception {
        ASTBlockNode root = mock(ASTBlockNode.class);
        ASTValueNode child = mock(ASTValueNode.class);
        Stream<ASTValueNode> childStream = Stream.of(child);
        when(root.getChildren()).thenReturn(childStream);

        query.translate(root, loader);
    }

    @Test(expected = SyntaxError.class)
    public void unrecognizedInputThrows() throws Exception {
        ASTValueNode root = mock(ASTValueNode.class);
        query.translate(root, loader);
    }
}