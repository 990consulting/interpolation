/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class ClassSymbolTest {

    private ClassSymbol query;
    private InstanceSymbolTable ist;
    private String description;

    @Before
    public void init() throws Exception {
        ist = mock(InstanceSymbolTable.class);
        description = "description";
        query = new ClassSymbol(() -> ist, description);
    }

    @Test
    public void testGetSymbolTable() throws Exception {
        InstanceSymbolTable actual = query.getSymbolTable();
        assertSame(ist, actual);
    }

    @Test
    public void testGetDescription() throws Exception {
        String actual = query.getDescription();
        assertSame(description, actual);
    }
}