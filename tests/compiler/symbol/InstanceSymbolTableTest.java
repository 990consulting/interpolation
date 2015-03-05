/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.util.UnrecognizedIdentifierError;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class InstanceSymbolTableTest {

    private ClassSymbolTable cst;
    private LoadedInstanceSymbolTable query;

    @Before
    public void init() throws Exception {
        cst = mock(ClassSymbolTable.class);
        query = new LoadedInstanceSymbolTable();
    }

    @Test
    public void testGetSymbolTable() throws Exception {
        ClassSymbolTable actual = query.getSymbolTable("test");
        assertSame(cst, actual);
    }

    @Test(expected = UnrecognizedIdentifierError.class)
    public void unrecognizedMemberThrows() throws Exception {
        query.getSymbolTable("something unrecognized");
    }
    private class LoadedInstanceSymbolTable extends InstanceSymbolTable {

        @Override
        protected HashMap<String, MemberSymbol> resolveMembers() {
            HashMap<String, MemberSymbol> ret = new HashMap<>(1);
            MemberSymbol symbol = new MemberSymbol(cst, "");
            ret.put("test", symbol);
            return ret;
        }
    }
}