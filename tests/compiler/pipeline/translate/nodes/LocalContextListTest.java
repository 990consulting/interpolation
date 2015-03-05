/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class LocalContextListTest {

    private LocalContextList query;
    private ObjectNode node;

    @Before
    public void init() throws Exception {
        query = new LocalContextList();
        node = mock(ObjectNode.class);
    }

    @Test
    public void testLoadGet() throws Exception {
        query.loadMember(node);
        ObjectNode actual = query.get(0);
        assertSame(node, actual);
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, query.size());
    }

    @Test
    public void testLoadSize() throws Exception {
        query.loadMember(node);
        assertEquals(1, query.size());
    }

    @Test
    public void testGetMembers() throws Exception {
        List<ObjectNode> expected = new ArrayList<>(0);
        List<ObjectNode> actual = query
                .getMembers()
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    public void testLoadGetMembers() throws Exception {
        query.loadMember(node);
        List<ObjectNode> expected = Stream.of(node)
                .collect(Collectors.toList());

        List<ObjectNode> actual = query
                .getMembers()
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetOutOfBoundsThrows() throws Exception {
        query.get(0);
    }
}