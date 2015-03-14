/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.context.ReservedContext;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class ListObjectNodeTest {

    private LocalContextList list;
    private ListSymbolTable symbolTable;
    private ListObjectNode query;
    private ReservedContext reserved;

    @Before
    public void init() throws Exception {
        symbolTable = mock(ListSymbolTable.class);
        list = mock(LocalContextList.class);
        reserved = mock(ReservedContext.class);
        query = new ListObjectNode(symbolTable, list, reserved);
    }

    @Test
    public void getStreamAsksList() throws Exception {
        Stream<ObjectNode> expected = mock(Stream.class);
        when(list.getMembers()).thenReturn(expected);
        Stream<ObjectNode> actual = query.getMemberStream();
        assertSame(expected, actual);
    }

    @Test
    public void getMemberAsksList() throws Exception {
        ObjectNode expected = mock(ObjectNode.class);
        when(list.size()).thenReturn(1);
        when(list.get(0)).thenReturn(expected);
        ObjectNode actual = query.getMember(0);
        assertSame(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getOutOfBoundsThrows() throws Exception {
        query.getMember(0);
    }

    @Test
    public void loadAsksList() throws Exception {
        ObjectNode node = mock(ObjectNode.class);
        query.loadMember(node);
        verify(list).loadMember(node);
    }

    @Test
    public void sizeAsksList() throws Exception {
        when(list.size()).thenReturn(5);
        assertEquals(5, query.size());
    }

    @Test
    public void getSymbolTable() throws Exception {
        ResolvingSymbolTable actual = query.getSymbolTable();
        assertSame(symbolTable, actual);
    }

    @Test
    public void getReserved() throws Exception {
        ReservedContext actual = query.getReserved();
        assertSame(reserved, actual);
    }

}