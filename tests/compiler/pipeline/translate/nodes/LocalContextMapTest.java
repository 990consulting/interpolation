/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.util.IllegalAssignmentError;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LocalContextMapTest {

    private LocalContextMap query;

    @Before
    public void init() throws Exception {
        query = new LocalContextMap();
    }

    @Test
    public void testGetMemberNamesEmpty() throws Exception {
        List<String> expected = new ArrayList<>();
        List<String> actual = query.
                getMemberIdentifiers().
                collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    public void testLoadGetMemberNames() throws Exception {
        query.loadMember("test", null);
        List<String> expected = Stream.of("test")
                .collect(Collectors.toList());

        List<String> actual = query.
                getMemberIdentifiers().
                collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    public void testLoadGetMember() throws Exception {
        ObjectNode expected = mock(ObjectNode.class);
        query.loadMember("test", expected);
        ObjectNode actual = query.getMember("test");
        assertSame(expected, actual);
    }

    @Test
    public void testHasMemberFalse() throws Exception {
        assertFalse(query.hasMember("test"));
    }

    @Test
    public void testHasMemberTrue() throws Exception {
        query.loadMember("test", null);
        assertTrue(query.hasMember("test"));
    }

    @Test(expected = IllegalAssignmentError.class)
    public void testLoadTwiceThrows() throws Exception {
        query.loadMember("test", null);
        query.loadMember("test", null);
    }

    @Test(expected = IllegalStateException.class)
    public void getUnloadedThrows() throws Exception {
        query.getMember("test");
    }
}