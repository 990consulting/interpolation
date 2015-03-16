/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.context;

import compiler.pipeline.translate.nodes.NestedContextSymbol;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class NestedContextSymbolTest {

    private Function<Object, Object> function;
    private Object instance;
    private Object output;
    private Consumer<Supplier<Object>> listener;
    private NestedContextSymbol<Object, Object> query;

    @Captor
    private ArgumentCaptor<Supplier<Object>> captor;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        function = mock(Function.class);
        instance = mock(Object.class);
        output = mock(Object.class);
        when(function.apply(instance)).thenReturn(output);
        listener = mock(Consumer.class);
        query = new NestedContextSymbol<>(function);
    }

    @Test
    public void addNotSuppliedUntilResolved() throws Exception {
        query.addListener(listener);
        verify(listener, never()).accept(any());
        query.broadcast(instance);
        verify(listener).accept(any());
    }

    @Test
    public void addThenBroadcast() throws Exception {
        query.addListener(listener);
        query.broadcast(instance);
        verifyPayload();
    }


    @Test(expected = IllegalStateException.class)
    public void broadcastThrowsIfResolved() throws Exception {
        query.broadcast(instance);
        query.broadcast(instance);
    }

    @Test
    public void addSuppliesIfResolved() throws Exception {
        query.broadcast(instance);
        query.addListener(listener);
        verifyPayload();
    }

    private void verifyPayload() {
        verify(listener).accept(captor.capture());
        Object actual = captor.getValue().get();
        assertSame(output, actual);
    }
}