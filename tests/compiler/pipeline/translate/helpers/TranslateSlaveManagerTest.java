/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.ListSymbolTable;
import compiler.symbol.MapSymbolTable;
import compiler.symbol.ReservedContext;
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
    public void initReachesHelpers() throws Exception {
        TranslationCallback walker = mock(TranslationCallback.class);
        query.init(walker);
        verify(listManager).init(walker);
        verify(mapManager).init(walker);
    }
}