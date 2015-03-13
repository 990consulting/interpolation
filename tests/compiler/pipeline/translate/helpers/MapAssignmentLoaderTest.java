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
import compiler.symbol.context.ReservedContext;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class MapAssignmentLoaderTest {

    private MapObjectNode node;
    private TranslationCallback callback;
    private ASTAssignmentNode toTranslate;
    private MapAssignmentLoader query;
    private ReservedContext reserved;
    @Before
    public void init() throws Exception {
        reserved = mock(ReservedContext.class);
        node = mock(MapObjectNode.class);
        when(node.getReserved()).thenReturn(reserved);

        callback = mock(TranslationCallback.class);

        ASTReferenceNode ref = new ASTReferenceNode("test");
        ASTValueNode value = mock(ASTValueNode.class);
        toTranslate = new ASTAssignmentNode(ref, value);

        query = new MapAssignmentLoader(node, callback);
    }

    @Test
    public void testLoadAssignment() throws Exception {
        ClassSymbolTable childCst = mock(ClassSymbolTable.class);
        when(node.getSymbolTable("test")).thenReturn(childCst);
        MapSymbolTable childIst = mock(MapSymbolTable.class);
        when(childCst.getSymbolTable(toTranslate.getValue())).thenReturn(childIst);
        ASTValueNode value = toTranslate.getValue();
        ObjectNode childValue = mock(ObjectNode.class);
        when(callback.walk(value, childIst, reserved)).thenReturn(childValue);

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