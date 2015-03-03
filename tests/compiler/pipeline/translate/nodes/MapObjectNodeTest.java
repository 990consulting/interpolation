/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.MapSymbolTable;
import compiler.symbol.SymbolTable;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class MapObjectNodeTest {

    private ContextMap map;
    private MapSymbolTable st;
    private MapObjectNode query;

    @Before
    public void init() throws Exception {
        map = mock(ContextMap.class);
        st = mock(MapSymbolTable.class);
        query = new MapObjectNode(st, map);
    }

    @Test
    public void getDefinedNamesAsksMap() throws Exception {
        Stream<String> expected = mock(Stream.class);
        when(map.getMemberNames()).thenReturn(expected);
        Stream<String> actual = query.getDefinedMemberNames();
        assertSame(expected, actual);
    }

    @Test
    public void getMemberAsksMap() throws Exception {
        ObjectNode expected = mock(ObjectNode.class);
        when(map.getMember("test")).thenReturn(expected);
        ObjectNode actual = query.getMember("test");
        assertSame(expected, actual);
    }
   
    @Test
    public void loadMemberAsksMap() throws Exception {
        ObjectNode expected = mock(ObjectNode.class);
        query.loadMember("test", expected);
        verify(map).loadMember("test", expected);
    }

    @Test
    public void getChildSymTableAsksMySymTable() throws Exception {
        SymbolTable expected = mock(SymbolTable.class);
        when(st.getSymbolTable("test")).thenReturn(expected);
        SymbolTable actual = query.getSymbolTableFor("test");
        assertSame(expected, actual);
    }
}