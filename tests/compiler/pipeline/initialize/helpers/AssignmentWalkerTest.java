/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.symbol.SymbolTable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.BiConsumer;

import static org.mockito.Mockito.*;

public class AssignmentWalkerTest {

    private BiConsumer<ASTNode, SymbolTable> walker;
    private AssignmentWalker query;
    private SymbolTable symbolTable;
    private ASTAssignmentNode toWalk;
    private ASTReferenceNode reference;
    private ASTValueNode value;

    @Before
    public void init() throws Exception {
        walker = mock(BiConsumer.class);
        symbolTable = mock(SymbolTable.class);
        query = new AssignmentWalker();
        query.init(walker);

        toWalk = mock(ASTAssignmentNode.class);
        reference = mock(ASTReferenceNode.class);
        when(toWalk.getReference()).thenReturn(reference);

        value = mock(ASTValueNode.class);
        when(toWalk.getValue()).thenReturn(value);
    }

    @Test
    public void walkInitializesSymbolTable() throws Exception {
        query.walk(toWalk, symbolTable);

        InOrder inOrder = inOrder(symbolTable);
        inOrder.verify(symbolTable).initSymbolTable(toWalk);
        inOrder.verify(symbolTable).getSymbolTable(toWalk);
    }

    @Test
    public void walkCallsWalker() throws Exception {
        SymbolTable childSymbolTable = mock(SymbolTable.class);
        when(symbolTable.getSymbolTable(toWalk)).thenReturn(childSymbolTable);
        query.walk(toWalk, symbolTable);
        verify(walker).accept(value, childSymbolTable);
    }

    @Test(expected = NotImplementedException.class)
    public void nestedReferenceNotImplemented() throws Exception {
        when(reference.hasChild()).thenReturn(true);
        query.walk(toWalk, symbolTable);
    }
}