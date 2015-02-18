/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate;

import compiler.nodes.AbstractReferenceNode;
import compiler.translate.visitors.ReferenceNameConverter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReferenceNameConverterTest {

    private ReferenceNameConverter query;

    @Before
    public void init() throws Exception {
        query = new ReferenceNameConverter();
    }
    @Test
    public void convertReturnsName() throws Exception {
        AbstractReferenceNode node = mock(AbstractReferenceNode.class);
        when(node.getName()).thenReturn("name");
        assertEquals("name", query.convert(node));
    }

    @Test(expected = IllegalStateException.class)
    public void nonLeafThrows() throws Exception {
        AbstractReferenceNode node = mock(AbstractReferenceNode.class);
        when(node.hasChild()).thenReturn(true);
        query.convert(node);
    }
}