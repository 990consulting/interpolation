/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dbborens on 2/17/15.
 */
public class TranslatorReferenceNode implements TranslatorNode {

    private String name;
    private TranslatorReferenceNode child;

    public TranslatorReferenceNode(String name) {
        this(name, null);
    }

    public TranslatorReferenceNode(String name, TranslatorReferenceNode child) {
        this.name = name;
        this.child = child;
    }

    @Override
    public String toString() {
        List<String> nameList = new ArrayList<>();
        appendNameTo(nameList);

        String ret = nameList
                .stream()
                .collect(Collectors.joining("->"));

        return ret;
    }

    @Override
    public TranslatorReferenceNode getType() {
        return this;
    }

    private void appendNameTo(List<String> list) {
        list.add(name);

        if (child != null) {
            child.appendNameTo(list);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslatorReferenceNode that = (TranslatorReferenceNode) o;

        if (child != null ? !child.equals(that.child) : that.child != null) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

}
