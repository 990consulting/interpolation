/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.helpers.TranslateSlaveManager;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.ListSymbolTable;
import compiler.symbol.MapSymbolTable;
import compiler.symbol.SymbolTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TranslationVisitorTest {

    private TranslateSlaveManager manager;
    private TranslationVisitor query;
    private ASTValueNode input;

    @Before
    public void init() throws Exception {
        manager = mock(TranslateSlaveManager.class);
        input = mock(ASTValueNode.class);

        query = new TranslationVisitor(manager);
    }

    /**
     * If the list is a ListSymbolTable, then the resulting
     * ObjectNode should be a ListObjectNode.
     */
    @Test
    public void listSymbolTableCase() throws Exception {
        ListSymbolTable st = mock(ListSymbolTable.class);
        ListObjectNode expected = mock(ListObjectNode.class);
        when(manager.translate(input, st)).thenReturn(expected);
        ObjectNode actual = query.translate(input, st);
        assertSame(expected, actual);
    }

    /**
     * If the list is a ListSymbolTable, then the resulting
     * ObjectNode should be a MapObjectNode.
     */
    @Test
    public void mapSymbolTableCase() throws Exception {
        MapSymbolTable st = mock(MapSymbolTable.class);
        MapObjectNode expected = mock(MapObjectNode.class);
        when(manager.translate(input, st)).thenReturn(expected);
        ObjectNode actual = query.translate(input, st);
        assertSame(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void otherSymbolTableThrows() throws Exception {
        SymbolTable st = mock(SymbolTable.class);
        query.translate(input, st);
    }
}