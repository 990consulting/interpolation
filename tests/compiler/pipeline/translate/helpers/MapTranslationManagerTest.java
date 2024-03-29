/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.NestedContext;
import compiler.symbol.tables.MapSymbolTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class MapTranslationManagerTest {

    private MapTranslationVisitor visitor;
    private MapAssignmentLoaderFactory factory;
    private MapTranslationManager query;

    @Before
    public void initTests() {
        visitor = mock(MapTranslationVisitor.class);
        factory = mock(MapAssignmentLoaderFactory.class);
        query = new MapTranslationManager(visitor, factory);
    }

    @Test
    public void init() throws Exception {
        TranslationCallback callback = mock(TranslationCallback.class);
        query.init(callback);
        verify(factory).init(callback);
    }

    @Test
    public void translateRoot() throws Exception {
        doTranslationTest(null);
    }

    @Test
    public void translateChild() throws Exception {
        NestedContext reserved = mock(NestedContext.class);
        doTranslationTest(reserved);
    }

    private void doTranslationTest(NestedContext reserved) throws Exception {
        MapAssignmentLoader loader = mock(MapAssignmentLoader.class);
        MapSymbolTable st = mock(MapSymbolTable.class);
        when(factory.build(st, reserved)).thenReturn(loader);
        MapObjectNode expected = mock(MapObjectNode.class);
        ASTValueNode node = mock(ASTValueNode.class);
        when(visitor.translate(node, loader)).thenReturn(expected);

        MapObjectNode actual = query.translate(node, st, reserved);
        assertSame(expected, actual);
    }


}