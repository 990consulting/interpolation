/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTDefinitionNode;
import compiler.interpret.nodes.ASTReferenceNode;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorReferenceNode;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SymbolTableTest {

    private SymbolTable query;
    private TranslatorReferenceNode type;
    private Symbol symbol;
    private TranslationHelper translator;
    @Before
    public void init() throws Exception {
        type = mock(TranslatorReferenceNode.class);
        symbol = mock(Symbol.class);

        translator = mock(TranslationHelper.class);
        when(symbol.getTranslator()).thenReturn(translator);

        query = new LoadedObjectSymbolTable(type);
    }

    @Test
    public void translatePassesToTranslator() {
        TranslatorNode expected = mock(TranslatorNode.class);
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        when(input.getReference()).thenReturn(new ASTReferenceNode("member"));
        when(translator.translate(input)).thenReturn(expected);

        TranslatorNode actual = query.translate(input);
        assertSame(expected, actual);
    }

    @Test(expected = NotImplementedException.class)
    public void translateDefinitionNotImplemented() throws Exception {
        ASTDefinitionNode input = mock(ASTDefinitionNode.class);
        query.translate(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unrecognizedMemberThrows() throws Exception {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);
        when(input.getReference()).thenReturn(new ASTReferenceNode("something unexpected"));
        query.translate(input);
    }

    @Test
    public void getType() throws Exception {
        assertSame(type, query.getType());
    }
    public class LoadedObjectSymbolTable extends SymbolTable {

        public LoadedObjectSymbolTable(TranslatorReferenceNode type) {
            super(type);
            member("member", symbol);
        }
    }
}