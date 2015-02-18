/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.nodes;

import compiler.nodes.AbstractReferenceNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.stream.Stream;

/**
 * A representation of a future Nanoverse object, including its
 * properties (both supplied and imputed), its type, and any user-
 * defined values. A hierarchy of ObjectNodes will be traversed
 * during final translation to compiled objects (or Java source).
 *
 * Created by dbborens on 2/16/15.
 */
public class TranslatorObjectNode implements TranslatorNode {

    private MemberIndex index;
    private TranslatorReferenceNode type;

    public TranslatorObjectNode(TranslatorReferenceNode type) {
        this.type = type;
        index = new MemberIndex();
    }

    public TranslatorObjectNode(TranslatorReferenceNode type, MemberIndex index) {
        this.type = type;
        this.index = index;
    }

    public void loadMember(AbstractReferenceNode node, TranslatorNode value) {
        index.loadMember(node, value);
    }

    public void loadUserDefined(String name, TranslatorNode value) {
        throw new NotImplementedException();
    }

    public boolean hasMember(String name) {
        return index.hasMember(name);
    }

    public TranslatorNode getMember(String name) {
        return index.getMember(name);
    }

    public TranslatorReferenceNode getType() {
        return type;
    }

    public Stream<String> getMemberStream() {
        return index.getMemberStream();
    }

}
