/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.*;
import compiler.symbol.SymbolTable;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SlaveManagerTest {

    private BlockWalker blockWalker;
    private AssignmentWalker assignmentWalker;
    private DefinitionWalker definitionWalker;
    private ReferenceWalker referenceWalker;
    private SymbolTable symbolTable;

    private SlaveManager query;

    @Before
    public void init() {
        blockWalker = mock(BlockWalker.class);
        assignmentWalker = mock(AssignmentWalker.class);
        definitionWalker = mock(DefinitionWalker.class);
        referenceWalker = mock(ReferenceWalker.class);
        symbolTable = mock(SymbolTable.class);

        query = new SlaveManager(assignmentWalker,
                blockWalker,
                definitionWalker,
                referenceWalker);
    }

    @Test
    public void assignmentNodeCase() throws Exception {
        ASTAssignmentNode assignmentNode = mock(ASTAssignmentNode.class);
        query.walk(assignmentNode, symbolTable);
        verify(assignmentWalker).walk(assignmentNode, symbolTable);
    }

    @Test
    public void blockNodeCase() throws Exception {
        ASTBlockNode blockNode = mock(ASTBlockNode.class);
        query.walk(blockNode, symbolTable);
        verify(blockWalker).walk(blockNode, symbolTable);
    }

    @Test
    public void definitionNodeCase() throws Exception {
        ASTDefinitionNode definitionNode = mock(ASTDefinitionNode.class);
        query.walk(definitionNode, symbolTable);
        verify(definitionWalker).walk(definitionNode, symbolTable);
    }

    @Test
    public void referenceNodeCase() throws Exception {
        ASTReferenceNode referenceNode = mock(ASTReferenceNode.class);
        query.walk(referenceNode, symbolTable);
        verify(referenceWalker).walk(referenceNode, symbolTable);
    }

    @Test
    public void initReachesHelpers() throws Exception {
        BiConsumer<ASTNode, SymbolTable> walker = mock(BiConsumer.class);
        query.init(walker);
        verify(referenceWalker).init(walker);
        verify(assignmentWalker).init(walker);
        verify(definitionWalker).init(walker);
        verify(blockWalker).init(walker);
    }
}