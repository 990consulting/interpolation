/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.nodes;

import compiler.nodes.AbstractReferenceNode;
import compiler.translate.ReferenceNameConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/17/15.
 */
public class MemberIndex {
    private Map<String, TranslatorNode> members;
    private ReferenceNameConverter converter;

    public MemberIndex() {
        this.members = new HashMap<>();
        this.converter = new ReferenceNameConverter();
    }

    public MemberIndex(Map<String, TranslatorNode> members, ReferenceNameConverter converter) {
        this.members = members;
        this.converter = converter;
    }

    public Stream<String> getMemberStream() {
        return members.keySet().stream();
    }

    public TranslatorNode getMember(String name) {
        if (!members.containsKey(name)) {
            throw new IllegalStateException("Retrieval of undefined member '" + name + "'");
        }

        return members.get(name);
    }

    public void loadMember(AbstractReferenceNode reference, TranslatorNode value) {
        String name = converter.convert(reference);
        if (members.containsKey(name)) {
            throw new IllegalArgumentException("Double assignment to member '" + name + "'");
        }
        members.put(name, value);
    }

    public boolean hasMember(String name) {
        return members.containsKey(name);
    }
}
