/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.symbol.ListSymbolTable;
import compiler.symbol.ReservedContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class ListTranslationManagerTest {

    private ListTranslationVisitor visitor;
    private ListValueLoaderFactory factory;
    private ListTranslationManager query;

    @Before
    public void initTests() {
        visitor = mock(ListTranslationVisitor.class);
        factory = mock(ListValueLoaderFactory.class);
        query = new ListTranslationManager(visitor, factory);
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
        ReservedContext reserved = mock(ReservedContext.class);
        doTranslationTest(reserved);
    }

    private void doTranslationTest(ReservedContext reserved) throws Exception {
        ListValueLoader loader = mock(ListValueLoader.class);
        ListSymbolTable st = mock(ListSymbolTable.class);
        when(factory.build(st, reserved)).thenReturn(loader);
        ListObjectNode expected = mock(ListObjectNode.class);
        ASTValueNode node = mock(ASTValueNode.class);
        when(visitor.translate(node, loader)).thenReturn(expected);

        ListObjectNode actual = query.translate(node, st, reserved);
        assertSame(expected, actual);
    }
}