/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.nodes.TypeNode;
import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.util.IllegalAssignmentError;
import compiler.util.SyntaxError;
import compiler.util.UnrecognizedIdentifierError;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class MapSymbolTableTest {

    private ReservedContext reservedContext;
    private LocalContext localContext;
    private TypeNode type;
    private MapSymbolTable query;

    @Before
    public void init() throws Exception {
        localContext = mock(LocalContext.class);
        reservedContext = mock(ReservedContext.class);
        type = mock(TypeNode.class);
        query = new LoadedMapSymbolTable(null);
    }

    @Test
    public void getType() throws Exception {
        TypeNode result = query.getType();
        assertSame(type, result);
    }

    @Test
    public void initSymbolTableForKnownSymbol() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        when(input.getReference()).thenReturn(new ASTReferenceNode("id"));

        ASTValueNode value = mock(ASTValueNode.class);
        when(input.getValue()).thenReturn(value);

        when(localContext.has("id")).thenReturn(true);

        Symbol symbol = mock(Symbol.class);
        when(localContext.get("id")).thenReturn(symbol);
        query.initSymbolTable(input);

        verify(symbol).resolve(value);
    }

    @Test(expected = SyntaxError.class)
    public void initSymbolOnNonAssignmentThrows() throws Exception {
        ASTValueNode input = mock(ASTValueNode.class);
        query.initSymbolTable(input);
    }

    @Test(expected = UnrecognizedIdentifierError.class)
    public void initSymbolTableForUnknownSymbolThrows() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        when(input.getReference()).thenReturn(new ASTReferenceNode("id"));
        query.initSymbolTable(input);
    }

    @Test(expected = IllegalAssignmentError.class)
    public void initSymbolTableForReservedKeywordThrows() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        when(input.getReference()).thenReturn(new ASTReferenceNode("id"));

        when(reservedContext.has("id")).thenReturn(true);
        query.initSymbolTable(input);
    }

    @Test
    public void getSymbolTableForKnownSymbol() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        when(input.getReference()).thenReturn(new ASTReferenceNode("id"));
        when(localContext.has("id")).thenReturn(true);

        Symbol symbol = mock(Symbol.class);
        SymbolTable expected = mock(SymbolTable.class);
        when(symbol.getSymbolTable()).thenReturn(expected);
        when(localContext.get("id")).thenReturn(symbol);

        SymbolTable actual = query.getSymbolTable(input);
        assertSame(expected, actual);
    }

    @Test(expected = UnrecognizedIdentifierError.class)
    public void getSymbolTableForUnknownSymbolThrows() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        when(input.getReference()).thenReturn(new ASTReferenceNode("id"));
        query.getSymbolTable(input);
    }

    @Test(expected = IllegalStateException.class)
    public void getSymbolTableForReservedKeywordThrows() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        when(input.getReference()).thenReturn(new ASTReferenceNode("id"));

        when(reservedContext.has("id")).thenReturn(true);
        query.getSymbolTable(input);
    }

    @Test(expected = NotImplementedException.class)
    public void initNestedReferenceNotImplemented() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        ASTReferenceNode reference = mock(ASTReferenceNode.class);
        when(reference.hasChild()).thenReturn(true);
        when(input.getReference()).thenReturn(reference);
        query.initSymbolTable(input);
    }

    @Test(expected = NotImplementedException.class)
    public void getNestedReferenceNotImplemented() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        ASTReferenceNode reference = mock(ASTReferenceNode.class);
        when(reference.hasChild()).thenReturn(true);
        when(input.getReference()).thenReturn(reference);
        query.initSymbolTable(input);
    }

    private class LoadedMapSymbolTable extends MapSymbolTable {

        public LoadedMapSymbolTable(ReservedContext parentReserved) {
            super(parentReserved);
        }

        @Override
        protected LocalContext resolveLocalContext() {
            return localContext;
        }

        @Override
        protected ReservedContext resolveReservedContext(ReservedContext parent) {
            return reservedContext;
        }

        @Override
        protected TypeNode resolveType() {
            return type;
        }
    }
}