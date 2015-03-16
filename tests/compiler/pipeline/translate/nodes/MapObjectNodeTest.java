/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;
import compiler.util.IllegalAssignmentError;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class MapObjectNodeTest {

    private LocalContextMap localMap;
    private NestedContext reserved;
    private MapSymbolTable st;
    private MapObjectNode query;

    @Before
    public void init() throws Exception {
        localMap = mock(LocalContextMap.class);
        st = mock(MapSymbolTable.class);
        reserved = mock(NestedContext.class);
        query = new MapObjectNode(st, reserved, localMap);
    }

    @Test
    public void getMemberIdentifiers() throws Exception {
        Stream<String> expected = mock(Stream.class);
        when(localMap.getMemberIdentifiers()).thenReturn(expected);
        Stream<String> actual = query.getMemberIdentifiers();
        assertSame(expected, actual);
    }

    // TODO Is this the behavior I want?
    @Test
    public void getMemberAsksReserved() throws Exception {
        when(reserved.has("test")).thenReturn(true);
        NestedContextSymbol expected = mock(NestedContextSymbol.class);
        when(reserved.get("test")).thenReturn(expected);
        Resolvable actual = query.getMember("test");
        Assert.assertSame(expected, actual);
    }

    @Test
    public void getMemberAsksMap() throws Exception {
        ObjectNode expected = mock(ObjectNode.class);
        when(localMap.getMember("test")).thenReturn(expected);
        Resolvable actual = query.getMember("test");
        assertSame(expected, actual);
    }

    @Test(expected = IllegalAssignmentError.class)
    public void loadOverReservedThrows() throws Exception {
        when(reserved.has("test")).thenReturn(true);
        ObjectNode node = mock(ObjectNode.class);
        query.loadMember("test", node);
    }

    @Test
    public void loadMemberAsksMap() throws Exception {
        ObjectNode expected = mock(ObjectNode.class);
        query.loadMember("test", expected);
        verify(localMap).loadMember("test", expected);
    }

    @Test
    public void getChildSymTableAsksMySymTable() throws Exception {
        ClassSymbolTable expected = mock(ClassSymbolTable.class);
        when(st.getSymbolTable("test")).thenReturn(expected);
        ResolvingSymbolTable actual = query.getSymbolTable("test");
        assertSame(expected, actual);
    }

    @Test(expected = IllegalAssignmentError.class)
    public void getSymTableForReservedThrows() throws Exception {
        when(reserved.has("test")).thenReturn(true);
        query.getSymbolTable("test");
    }

    @Test
    public void getReserved() throws Exception {
        NestedContext actual = query.getReserved();
        assertSame(reserved, actual);
    }
}