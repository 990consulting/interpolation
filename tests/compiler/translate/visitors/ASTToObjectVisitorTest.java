/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.visitors;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTBlockNode;
import compiler.interpret.nodes.ASTDefinitionNode;
import compiler.interpret.nodes.ASTValueNode;
import compiler.translate.nodes.TranslatorObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Supplier;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class ASTToObjectVisitorTest {

    private ASTToObjectVisitor query;
    private Supplier<TranslatorObjectNode> supplier;
    private ASTObjAssignmentVisitor asnVisitor;
    private ASTObjBlockVisitor blkVisitor;

    @Before
    public void init() throws Exception {
        supplier = mock(Supplier.class);
        asnVisitor = mock(ASTObjAssignmentVisitor.class);
        blkVisitor = mock(ASTObjBlockVisitor.class);
        query = new ASTToObjectVisitor(asnVisitor, blkVisitor, supplier);
    }

    @Test
    public void assignmentPassedToAssignmentVisitor() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        query.visit(input);
        verify(asnVisitor).visit(input);
    }

    @Test
    public void blockPassedToBlockVisitor() throws Exception {
        ASTBlockNode input = mock(ASTBlockNode.class);
        query.visit(input);
        verify(blkVisitor).visit(input);
    }

    // Definition input is not yet handled.
    @Test(expected = NotImplementedException.class)
    public void definitionNotYetImplemented() throws Exception {
        ASTDefinitionNode definitionNode = mock(ASTDefinitionNode.class);
        query.visit(definitionNode);
    }

    // Input can only be an assignment, a block or a definition.
    @Test(expected = IllegalArgumentException.class)
    public void illegalInputThrows() throws Exception {
        ASTValueNode input = mock(ASTValueNode.class);
        query.visit(input);
    }

    @Test
    public void returnAsksSupplier() throws Exception {
        ASTBlockNode input = mock(ASTBlockNode.class);
        TranslatorObjectNode expected = mock(TranslatorObjectNode.class);
        when(supplier.get()).thenReturn(expected);

        TranslatorObjectNode actual = query.visit(input);
        assertSame(expected, actual);
    }

    @Test
    public void returnAfterVisiting() throws Exception {
        ASTBlockNode input = mock(ASTBlockNode.class);
        query.visit(input);

        InOrder inOrder = inOrder(blkVisitor, supplier);
        inOrder.verify(blkVisitor).visit(input);
        inOrder.verify(supplier).get();
        inOrder.verifyNoMoreInteractions();
    }
}