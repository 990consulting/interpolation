/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.pipeline.build.builders.Builder;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;
import compiler.util.UnrecognizedIdentifierError;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.function.Function;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class MapSymbolTableTest {

    private ClassSymbolTable cst;
    private LoadedMapSymbolTable query;

    @Before
    public void init() throws Exception {
        cst = mock(ClassSymbolTable.class);
        query = new LoadedMapSymbolTable();
    }

    @Test
    public void testGetSymbolTable() throws Exception {
        ResolvingSymbolTable actual = query.getSymbolTable("test");
        assertSame(cst, actual);
    }

    @Test(expected = UnrecognizedIdentifierError.class)
    public void unrecognizedMemberThrows() throws Exception {
        query.getSymbolTable("something unrecognized");
    }

    private class LoadedMapSymbolTable extends MapSymbolTable {

        @Override
        protected HashMap<String, MemberSymbol> resolveMembers() {
            HashMap<String, MemberSymbol> ret = new HashMap<>(1);
            MemberSymbol symbol = new MemberSymbol(cst, "");
            ret.put("test", symbol);
            return ret;
        }

        @Override
        protected HashMap<String, Function> resolveReserved() {
            return new HashMap<>();
        }

        @Override
        public Builder getBuilder() {
            return mock(Builder.class);
        }
    }
}