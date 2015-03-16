/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build.builders;

import compiler.pipeline.build.MapBuilder;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.NestedContextSymbol;
import compiler.pipeline.translate.nodes.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MapBuilderTest {

    private Object instance;
    private HashMap<String, Object> assignedFields;
    private HashSet<String> listeningFields;
    private Consumer<Object> callback;
    private LoadedMapBuilder query;
    private MapObjectNode node;

    @Captor
    private ArgumentCaptor<Object> captor;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        assignedFields = new HashMap<>();
        listeningFields = new HashSet<>();
        instance = mock(Object.class);
        callback = mock(Consumer.class);
        node = mock(MapObjectNode.class);
        when(node.getMemberIdentifiers())
                .thenReturn(Stream.of("a"));

        query = new LoadedMapBuilder(assignedFields, listeningFields);
    }

    @Test
    public void visitResolvesKeywordValuedMembers() {
        Object childInstance = mock(Object.class);
        ObjectNode child = cb -> cb.accept(childInstance);
        when(node.getMember("a")).thenReturn(child);
        query.visit(node, callback);
        assertFalse(listeningFields.contains("a"));
        assertEquals(childInstance, assignedFields.get("a"));
    }

    @Test
    public void instantiateCalledAfterLastFieldResolved() {
        Object childInstance = mock(Object.class);
        ObjectNode child = cb -> cb.accept(childInstance);
        when(node.getMember("a")).thenReturn(child);
        query.visit(node, callback);
        verify(callback).accept(instance);
    }

    @Test
    public void visitListensForSymbols() {
        NestedContextSymbol symbol = mock(NestedContextSymbol.class);
        when(node.getMember("a")).thenReturn(symbol);
        query.visit(node, callback);
        assertTrue(listeningFields.contains("a"));
        verify(symbol).addListener(any());
    }

    @Test
    public void visitUpdatesWhenSymbolsAreInstantiated() {
        NestedContextSymbol symbol = mock(NestedContextSymbol.class);
        when(node.getMember("a")).thenReturn(symbol);
        query.visit(node, callback);
        ArgumentCaptor<Consumer> listenerCaptor = ArgumentCaptor.forClass(Consumer.class);
        verify(symbol).addListener(listenerCaptor.capture());
        Consumer<Supplier> listener = (Consumer<Supplier>) listenerCaptor.getValue();
        Supplier supplier = mock(Supplier.class);
        listener.accept(supplier);
        assertEquals(supplier, assignedFields.get("a"));
        assertFalse(listeningFields.contains("a"));
    }

    @Test(expected = IllegalStateException.class)
    public void missingFieldsThrows() {
        when(node.getMemberIdentifiers()).thenReturn(Stream.empty());
        query.visit(node, callback);
    }

    @Test
    public void visitInstantiatesAfterLastBroadcast() {
        NestedContextSymbol symbol = mock(NestedContextSymbol.class);
        when(node.getMember("a")).thenReturn(symbol);
        query.visit(node, callback);
        ArgumentCaptor<Consumer> listenerCaptor = ArgumentCaptor.forClass(Consumer.class);
        verify(symbol).addListener(listenerCaptor.capture());
        Consumer<Supplier> listener = (Consumer<Supplier>) listenerCaptor.getValue();
        Supplier supplier = mock(Supplier.class);
        listener.accept(supplier);
        verify(callback).accept(instance);
    }

    private class LoadedMapBuilder extends MapBuilder {

        public LoadedMapBuilder(Map assignedFields, Set listeningFields) {
            super(assignedFields, listeningFields);
        }

        @Override
        protected Map<String, Class> resolveFields() {
            Map ret = new HashMap<>();
            ret.put("a", Object.class);
            return ret;
        }

        @Override
        protected Object instantiate() {
            return instance;
        }
    }
}
