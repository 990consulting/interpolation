/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.symbol.SymbolTable;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.mockito.Mockito.*;

public class ReferenceWalkerTest {

    private ReferenceWalker query;
    private SymbolTable symbolTable;
    private ASTReferenceNode toWalk;

    @Before
    public void init() throws Exception {
        symbolTable = mock(SymbolTable.class);
        toWalk = mock(ASTReferenceNode.class);
        query = new ReferenceWalker();
    }

    @Test
    public void walkInitializesSymbolTable() throws Exception {
        query.walk(toWalk, symbolTable);
        verify(symbolTable).initSymbolTable(toWalk);
    }

    @Test(expected = NotImplementedException.class)
    public void nestedReferenceNotImplemented() throws Exception {
        when(toWalk.hasChild()).thenReturn(true);
        query.walk(toWalk, symbolTable);
    }
}