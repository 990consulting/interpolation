/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.nodes;

import compiler.nodes.AbstractReferenceNode;
import compiler.translate.nodes.MemberIndex;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorObjectNode;
import compiler.translate.nodes.TranslatorReferenceNode;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TranslatorObjectNodeTest {

    private TranslatorObjectNode query;
    private TranslatorReferenceNode type;
    private MemberIndex index;

    @Before
    public void init() throws Exception {
        type = mock(TranslatorReferenceNode.class);
        index = mock(MemberIndex.class);
        query = new TranslatorObjectNode(type, index);
    }

    @Test
    public void loadMemberPassesToIndex() throws Exception {
        AbstractReferenceNode reference = mock(AbstractReferenceNode.class);
        TranslatorNode value = mock(TranslatorNode.class);
        query.loadMember(reference, value);
        verify(index).loadMember(reference, value);
    }

    @Test
    public void hasMemberAsksIndex() throws Exception {
        when(index.hasMember("member")).thenReturn(true);
        assertTrue(query.hasMember("member"));
    }

    @Test
    public void getMemberAsksIndex() throws Exception {
        TranslatorNode expected = mock(TranslatorNode.class);
        when(index.getMember("member")).thenReturn(expected);
        assertSame(expected, query.getMember("member"));
    }

    @Test
    public void getMemberStreamAsksIndex() throws Exception {
        Stream<String> expected = mock(Stream.class);
        when(index.getMemberStream()).thenReturn(expected);
        assertSame(expected, query.getMemberStream());
    }

    @Test(expected = NotImplementedException.class)
    public void loadUserDefined() throws Exception {
        TranslatorNode expected = mock(TranslatorNode.class);
        query.loadUserDefined("userDefined", expected);
    }

    @Test
    public void getType() throws Exception {
        assertEquals(type, query.getType());
    }
}