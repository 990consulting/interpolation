/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.nodes;

import compiler.interpret.nodes.ASTReferenceNode;
import compiler.translate.ReferenceToAssignmentConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MemberIndexTest {

    private MemberIndex query;
    private Map<String, TranslatorNode> map;
    private ReferenceToAssignmentConverter converter;
    private ASTReferenceNode reference;
    private TranslatorNode value;

    @Before
    public void init() {
        converter = mock(ReferenceToAssignmentConverter.class);
        map = new HashMap<>();
        query = new MemberIndex(map, converter);
        reference = mock(ASTReferenceNode.class);
        when(converter.convert(reference)).thenReturn("member");
        value = mock(TranslatorNode.class);
    }

    @Test
    public void loadHasMember() throws Exception {
        query.loadMember(reference, value);
        assertTrue(query.hasMember("member"));
    }

    @Test
    public void loadGetMember() throws Exception {
        query.loadMember(reference, value);
        assertSame(value, query.getMember("member"));
    }

    @Test
    public void getMemberStream() throws Exception {
        query.loadMember(reference, value);
        List<String> expected = Collections.singletonList("member");
        List<String> actual = query
                .getMemberStream()
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void loadAlreadyDefinedThrows() throws Exception {
        query.loadMember(reference, value);
        query.loadMember(reference, value);
    }

    @Test(expected = IllegalStateException.class)
    public void getUndefinedThrows() throws Exception {
        query.getMember("member");
    }

}