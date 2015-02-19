/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.nodes;

import compiler.interpret.nodes.ASTReferenceNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TranslatorObjectNodeBuilderTest {

    private TranslatorObjectNodeBuilder query;
    private TranslatorReferenceNode parentType;
    private ASTReferenceNode childReference;
    private TranslatorNode childValue;
    private String name;

    @Before
    public void init() {
        name = "name";
        parentType = mock(TranslatorReferenceNode.class);
        query = new TranslatorObjectNodeBuilder(parentType);
        childReference = mock(ASTReferenceNode.class);
        when(childReference.getName()).thenReturn(name);
        childValue = mock(TranslatorNode.class);
    }

    @Test
    public void referenceGetsAddedAsName() throws Exception {
        query.load(childReference, childValue);
        TranslatorObjectNode result = query.build();
        assertEquals(childValue, result.getMember(name));
    }

    @Test(expected = IllegalArgumentException.class)
    public void duplicateReferenceThrows() throws Exception {
        query.load(childReference, childValue);
        query.load(childReference, childValue);
    }

    @Test
    public void parentHasAssignedType() throws Exception {
        TranslatorObjectNode result = query.build();
        assertSame(parentType, result.getType());
    }

    @Test(expected = IllegalStateException.class)
    public void buildTwiceThrows() throws Exception {
        query.build();
        query.build();
    }
}