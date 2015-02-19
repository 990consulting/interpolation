/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.visitors;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTBlockNode;
import compiler.interpret.nodes.ASTDefinitionNode;
import compiler.interpret.nodes.ASTStatementNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class ASTObjBlockVisitorTest {

    private ASTObjAssignmentVisitor assignmentVisitor;
    private ASTObjBlockVisitor query;

    @Before
    public void init() throws Exception {
        assignmentVisitor = mock(ASTObjAssignmentVisitor.class);
        query = new ASTObjBlockVisitor(assignmentVisitor);
    }

    @Test
    public void assignmentChildPassedToAssignmentVisitor() {
        ASTAssignmentNode child1 = mock(ASTAssignmentNode.class);
        ASTAssignmentNode child2 = mock(ASTAssignmentNode.class);

        loadChildrenAndVisit(child1, child2);

        InOrder inOrder = inOrder(assignmentVisitor);
        inOrder.verify(assignmentVisitor).visit(child1);
        inOrder.verify(assignmentVisitor).visit(child2);
        inOrder.verifyNoMoreInteractions();
    }

    @Test(expected = NotImplementedException.class)
    public void definitionChildNotImplemented() {
        ASTDefinitionNode child = mock(ASTDefinitionNode.class);
        loadChildrenAndVisit(child);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalChildClassThrows() {
        ASTStatementNode child = mock(ASTStatementNode.class);
        loadChildrenAndVisit(child);
    }

    private void loadChildrenAndVisit(ASTStatementNode... childNodes) {
        Stream<ASTStatementNode> children = Stream.of(childNodes);
        ASTBlockNode input = mock(ASTBlockNode.class);
        when(input.getChildren()).thenReturn(children);
        query.visit(input);
    }
}