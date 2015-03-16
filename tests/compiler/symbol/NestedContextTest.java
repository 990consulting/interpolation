/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.pipeline.translate.nodes.NestedContext;
import compiler.pipeline.translate.nodes.NestedContextSymbol;
import compiler.util.UnrecognizedIdentifierError;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dbborens on 2/21/15.
 */
public class NestedContextTest  {

    private NestedContext query;
    private NestedContext parent;
    private String identifier;
    private NestedContextSymbol symbol;

    @Before
    public void init() throws Exception {
        parent = mock(NestedContext.class);
        query = new NestedContext(parent);
        symbol = mock(NestedContextSymbol.class);
        identifier = "identifier";
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
    public void putOccludesParent() throws Exception {
        when(parent.get(identifier)).thenReturn(mock(NestedContextSymbol.class));
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

    @Test
    public void notHasChecksParent() throws Exception {
        when(parent.has(identifier)).thenReturn(false);
        assertFalse(query.has(identifier));
    }

    @Test
    public void rootCaseNotHas() throws Exception {
        query = new NestedContext();
        assertFalse(query.has(identifier));
    }

    @Test(expected = UnrecognizedIdentifierError.class)
    public void getUnassignedThrows() throws Exception {
        when(parent.has(identifier)).thenReturn(false);
        query.get(identifier);
    }

    @Test(expected = UnrecognizedIdentifierError.class)
    public void rootCaseGetUnassignedThrows() throws Exception {
        query = new NestedContext();
        query.get(identifier);
    }
}
