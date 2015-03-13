/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.symbol.context.ReservedContext;
import compiler.symbol.tables.ListSymbolTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ListValueLoaderFactoryTest {

    @Test
    public void testBuild() throws Exception {
        TranslationCallback callback = mock(TranslationCallback.class);
        ListValueLoaderFactory query = new ListValueLoaderFactory();
        query.init(callback);
        ListSymbolTable st = mock(ListSymbolTable.class);
        ReservedContext reserved = mock(ReservedContext.class);
        ListObjectNode expectedNode = new ListObjectNode(st, reserved);
        ListValueLoader expected = new ListValueLoader(expectedNode, callback);
        ListValueLoader actual = query.build(st, reserved);
        assertEquals(expected, actual);
    }
}