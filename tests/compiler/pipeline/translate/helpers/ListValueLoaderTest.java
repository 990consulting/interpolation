/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.ClassSymbolTable;
import compiler.symbol.InstanceSymbolTable;
import compiler.symbol.ReservedContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class ListValueLoaderTest {

    private ListObjectNode node;
    private TranslationCallback callback;
    private ASTValueNode toTranslate;
    private ListValueLoader query;
    private ReservedContext reserved;
    @Before
    public void init() throws Exception {
        reserved = mock(ReservedContext.class);
        node = mock(ListObjectNode.class);
        when(node.getReserved()).thenReturn(reserved);

        callback = mock(TranslationCallback.class);

        ASTReferenceNode ref = new ASTReferenceNode("test");
        ASTValueNode value = mock(ASTValueNode.class);
        toTranslate = new ASTAssignmentNode(ref, value);

        query = new ListValueLoader(node, callback);
    }

    @Test
    public void testLoadValue() throws Exception {
        ClassSymbolTable cst = mock(ClassSymbolTable.class);
        when(node.getSymbolTable()).thenReturn(cst);
        InstanceSymbolTable ist = mock(InstanceSymbolTable.class);
        when(cst.getSymbolTable(toTranslate)).thenReturn(ist);
        ObjectNode childValue = mock(ObjectNode.class);
        when(callback.walk(toTranslate, ist, reserved)).thenReturn(childValue);

        query.loadAssignment(toTranslate);
        verify(node).loadMember(childValue);
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