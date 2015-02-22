/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.util.UnrecognizedIdentifierException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractNestedContextTest {

    protected AbstractNestedContext query;
    protected AbstractNestedContext parent;
    protected String identifier;
    protected Symbol symbol;

    @Before
    public void init() throws Exception {
        parent = mock(AbstractNestedContext.class);
        query = getQuery(parent);
        identifier = "identifier";
        symbol = mock(Symbol.class);
    }

    protected abstract AbstractNestedContext getQuery(AbstractNestedContext parent);

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
    public void putOccludesParent() throws Exception {
        when(parent.get(identifier)).thenReturn(mock(Symbol.class));
        query.put(identifier, symbol);
        assertSame(symbol, query.get(identifier));
    }

    @Test
    public void getChecksParent() throws Exception {
        when(parent.has(identifier)).thenReturn(true);
        when(parent.get(identifier)).thenReturn(symbol);
        assertSame(symbol, query.get(identifier));
    }

    @Test
    public void hasChecksParent() throws Exception {
        when(parent.has(identifier)).thenReturn(true);
        assertTrue(query.has(identifier));
    }


    @Before
    public void notHasChecksParent() throws Exception {
        when(parent.has(identifier)).thenReturn(false);
        assertFalse(query.has(identifier));
    }

    @Test
    public void rootCaseNotHas() throws Exception {
        query = new AbstractNestedContext();
        assertFalse(query.has(identifier));
    }
    @Test(expected = UnrecognizedIdentifierException.class)
    public void getUnassignedThrows() throws Exception {
        when(parent.has(identifier)).thenReturn(false);
        query.get(identifier);
    }

    @Test(expected = UnrecognizedIdentifierException.class)
    public void rootCaseGetUnassignedThrows() throws Exception {
        query = new AbstractNestedContext();
        query.get(identifier);
    }

}