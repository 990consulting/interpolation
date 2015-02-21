/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.visitors;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTReferenceNode;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorObjectNodeBuilder;
import compiler.translate.symbols.SymbolTable;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.mockito.Mockito.*;

public class ASTObjAssignmentVisitorTest {

    private ASTObjAssignmentVisitor query;
    private SymbolTable symbolTable;
    private TranslatorObjectNodeBuilder builder;
    private ASTAssignmentNode input;
    @Before
    public void init() throws Exception {
        symbolTable = mock(SymbolTable.class);
        builder = mock(TranslatorObjectNodeBuilder.class);
        input = mock(ASTAssignmentNode.class);
        query = new ASTObjAssignmentVisitor(symbolTable, builder);
    }

    @Test
    public void translationIsPassedToBuilder() throws Exception {
        TranslatorNode result = mock(TranslatorNode.class);
        when(symbolTable.translate(input)).thenReturn(result);
        ASTReferenceNode reference = mock(ASTReferenceNode.class);
        when(input.getReference()).thenReturn(reference);
        query.visit(input);
        verify(builder).load(reference, result);
    }

    @Test(expected = NotImplementedException.class)
    public void nestedScopeNotImplemented() throws Exception {
        ASTReferenceNode reference = mock(ASTReferenceNode.class);
        when(reference.hasChild()).thenReturn(true);
        when(input.getReference()).thenReturn(reference);
        query.visit(input);
    }
}