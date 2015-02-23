/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.util.UnrecognizedIdentifierError;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LocalContextTest {

    private LocalContext query;
    private String identifier;
    private Symbol symbol;

    @Before
    public void init() throws Exception {
        query = new LocalContext();
        identifier = "identifier";
        symbol = mock(Symbol.class);
    }

    @Test
    public void putGet() throws Exception {
        query.put(identifier, symbol);
        assertSame(symbol, query.get(identifier));
    }

    @Test
    public void putHas() throws Exception {
        query.put(identifier, symbol);
        assertTrue(query.has(identifier));
    }

    @Test
    public void notHas() throws Exception {
        assertFalse(query.has(identifier));
    }

    @Test(expected = UnrecognizedIdentifierError.class)
    public void getUnassignedThrows() throws Exception {
        query.get(identifier);
    }
}