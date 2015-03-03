/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.SymbolTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class MapAssignmentLoaderTest {

    private MapObjectNode node;
    private TranslationCallback callback;
    private ASTAssignmentNode toTranslate;
    private MapAssignmentLoader query;
    @Before
    public void init() throws Exception {
        node = mock(MapObjectNode.class);
        callback = mock(TranslationCallback.class);

        ASTReferenceNode ref = new ASTReferenceNode("test");
        ASTValueNode value = mock(ASTValueNode.class);
        toTranslate = new ASTAssignmentNode(ref, value);

        query = new MapAssignmentLoader(node, callback);
    }

    @Test
    public void testLoadAssignment() throws Exception {
        SymbolTable childSt = mock(SymbolTable.class);
        when(node.getSymbolTableFor("test")).thenReturn(childSt);
        ASTValueNode value = toTranslate.getValue();
        ObjectNode childValue = mock(ObjectNode.class);
        when(callback.walk(value, childSt)).thenReturn(childValue);

        query.loadAssignment(toTranslate);
        verify(node).loadMember("test", childValue);
    }

    @Test(expected = IllegalStateException.class)
    public void loadAfterFinishThrows() throws Exception {
        query.finish();
        query.loadAssignment(toTranslate);
    }

    @Test
    public void testFinish() throws Exception {
        ObjectNode actual = query.finish();
        assertSame(node, actual);
    }
}