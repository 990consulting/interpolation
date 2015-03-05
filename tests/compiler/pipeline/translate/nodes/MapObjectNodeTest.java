/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.ClassSymbolTable;
import compiler.symbol.InstanceSymbolTable;
import compiler.symbol.ReservedContext;
import compiler.util.IllegalAssignmentError;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class MapObjectNodeTest {

    private LocalContextMap localMap;
    private ReservedContext reserved;
    private InstanceSymbolTable st;
    private MapObjectNode query;

    @Before
    public void init() throws Exception {
        localMap = mock(LocalContextMap.class);
        st = mock(InstanceSymbolTable.class);
        reserved = mock(ReservedContext.class);
        query = new MapObjectNode(st, reserved, localMap);
    }

//    @Test
//    public void getDefinedNamesAsksMap() throws Exception {
//        Stream<String> expected = mock(Stream.class);
//        when(localMap.getMemberIdentifiers()).thenReturn(expected);
//        Stream<String> actual = query.getDefinedMemberNames();
//        assertSame(expected, actual);
//    }

//    @Test
//    public void getMemberAsksMap() throws Exception {
//        ObjectNode expected = mock(ObjectNode.class);
//        when(localMap.getMember("test")).thenReturn(expected);
//        ObjectNode actual = query.getMember("test");
//        assertSame(expected, actual);
//    }
   
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
        ClassSymbolTable actual = query.getSymbolTable("test");
        assertSame(expected, actual);
    }

    @Test(expected = IllegalAssignmentError.class)
    public void getSymTableForReservedThrows() throws Exception {
        when(reserved.has("test")).thenReturn(true);
        query.getSymbolTable("test");
    }

    @Test
    public void getReserved() throws Exception {
        ReservedContext actual = query.getReserved();
        assertSame(reserved, actual);
    }
}