/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class MemberSymbolTest {

    private MemberSymbol query;
    private String description;
    private ClassSymbolTable cst;

    @Before
    public void init() throws Exception {
        cst = mock(ClassSymbolTable.class);
        description = "description";
        query = new MemberSymbol(cst, description);
    }

    @Test
    public void getSymbolTable() throws Exception {
        ClassSymbolTable actual = query.getSymbolTable();
        assertSame(cst, actual);
    }

    @Test
    public void getDescription() throws Exception {
        String actual = query.getDescription();
        assertSame(description, actual);
    }
}