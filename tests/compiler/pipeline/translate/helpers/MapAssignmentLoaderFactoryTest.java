/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.MapSymbolTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class MapAssignmentLoaderFactoryTest {

    @Test
    public void testBuild() throws Exception {
        TranslationCallback callback = mock(TranslationCallback.class);
        MapAssignmentLoaderFactory query = new MapAssignmentLoaderFactory();
        query.init(callback);
        MapSymbolTable st = mock(MapSymbolTable.class);
        MapObjectNode expectedNode = new MapObjectNode(st);
        MapAssignmentLoader expected = new MapAssignmentLoader(expectedNode, callback);
        MapAssignmentLoader actual = query.build(st);
        assertEquals(expected, actual);
    }
}