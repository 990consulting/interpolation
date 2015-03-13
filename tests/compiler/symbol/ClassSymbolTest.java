/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.InstantiableSymbolTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class ClassSymbolTest {

    private ClassSymbol query;
    private InstantiableSymbolTable ist;
    private String description;

    @Before
    public void init() throws Exception {
        ist = mock(InstantiableSymbolTable.class);
        description = "description";
        query = new ClassSymbol(() -> ist, description);
    }

    @Test
    public void testGetSymbolTable() throws Exception {
        InstantiableSymbolTable actual = query.getSymbolTable();
        assertSame(ist, actual);
    }

    @Test
    public void testGetDescription() throws Exception {
        String actual = query.getDescription();
        assertSame(description, actual);
    }
}