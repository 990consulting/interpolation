/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize;

import compiler.pipeline.initialize.helpers.SlaveManager;
import compiler.pipeline.interpret.nodes.*;
import compiler.symbol.SymbolTable;
import compiler.symbol.tables.RootSymbolTable;
import compiler.util.UserError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class InitializationWalkerTest {

    private SlaveManager slaveManager;
    private ExposedInitializationWalker query;
    private SymbolTable symbolTable;

    @Before
    public void init() throws Exception {
        slaveManager = mock(SlaveManager.class);
        symbolTable = mock(SymbolTable.class);

        query = new ExposedInitializationWalker(slaveManager);
    }


    @Test
    public void walk() throws Exception {
        ASTRootNode root = mock(ASTRootNode.class);
        query.walk(root);
        ArgumentCaptor<SymbolTable> stCaptor = ArgumentCaptor.forClass(SymbolTable.class);
        verify(slaveManager).walk(eq(root), stCaptor.capture());
        SymbolTable captured = stCaptor.getValue();
        assertTrue(captured instanceof RootSymbolTable);
    }

    @Test
    public void assignmentCase() throws Exception {
        ASTAssignmentNode child = mock(ASTAssignmentNode.class);
        query.walkChildren(child, symbolTable);
        verify(slaveManager).walk(child, symbolTable);
    }

    @Test
    public void definitionCase() throws Exception {
        ASTDefinitionNode child = mock(ASTDefinitionNode.class);
        query.walkChildren(child, symbolTable);
        verify(slaveManager).walk(child, symbolTable);
    }

    @Test
    public void blockCase() throws Exception {
        ASTBlockNode child = mock(ASTBlockNode.class);
        query.walkChildren(child, symbolTable);
        verify(slaveManager).walk(child, symbolTable);
    }

    @Test
    public void referenceCase() throws Exception {
        ASTReferenceNode child = mock(ASTReferenceNode.class);
        query.walkChildren(child, symbolTable);
        verify(slaveManager).walk(child, symbolTable);
    }

    @Test
    public void primitiveCase() throws Exception {
        ASTPrimitiveNode child = mock(ASTPrimitiveNode.class);
        query.walkChildren(child, symbolTable);
        verifyNoMoreInteractions(slaveManager);
    }

    private class ExposedInitializationWalker extends InitializationWalker {

        public ExposedInitializationWalker(SlaveManager slaveManager) {
            super(slaveManager);
        }

        @Override
        public void walkChildren(ASTValueNode toWalk, SymbolTable symbolTable) throws UserError {
            super.walkChildren(toWalk, symbolTable);
        }
    }
}