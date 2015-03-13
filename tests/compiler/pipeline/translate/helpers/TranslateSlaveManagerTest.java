/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.PrimitiveNode;
import compiler.symbol.context.ReservedContext;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.primitive.PrimitiveSymbolTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class TranslateSlaveManagerTest {

    private ListTranslationManager listManager;
    private MapTranslationManager mapManager;
    private TranslateSlaveManager query;
    private ASTValueNode node;
    private ReservedContext reserved;

    @Before
    public void init() throws Exception {
        reserved = mock(ReservedContext.class);
        listManager = mock(ListTranslationManager.class);
        mapManager = mock(MapTranslationManager.class);
        query = new TranslateSlaveManager(listManager, mapManager);
        node = mock(ASTValueNode.class);
    }
    @Test
    public void listSymbolTableCase() throws Exception {
        ListSymbolTable st = mock(ListSymbolTable.class);
        ListObjectNode expected = mock(ListObjectNode.class);
        when(listManager.translate(node, st, reserved)).thenReturn(expected);
        ListObjectNode actual = query.translate(node, st, reserved);
        assertSame(expected, actual);
    }

    @Test
    public void mapSymbolTableCase() throws Exception {
        MapSymbolTable st = mock(MapSymbolTable.class);
        MapObjectNode expected = mock(MapObjectNode.class);
        when(mapManager.translate(node, st, reserved)).thenReturn(expected);
        MapObjectNode actual = query.translate(node, st, reserved);
        assertSame(expected, actual);
    }

    @Test
    public void primitiveSymbolTableCase() throws Exception {
        PrimitiveSymbolTable pst = mock(PrimitiveSymbolTable.class);
        ASTPrimitiveNode node = mock(ASTPrimitiveNode.class);
        PrimitiveNode expected = mock(PrimitiveNode.class);
        when(pst.convert(node)).thenReturn(expected);
        PrimitiveNode actual =  query.translate(node, pst);
        assertSame(expected, actual);
    }

    @Test
    public void initReachesHelpers() throws Exception {
        TranslationCallback walker = mock(TranslationCallback.class);
        query.init(walker);
        verify(listManager).init(walker);
        verify(mapManager).init(walker);
    }
}