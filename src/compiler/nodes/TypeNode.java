/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by dbborens on 2/22/15.
 */
public class TypeNode implements Node {

    private String identifier;
    private TypeNode supertype;

    public TypeNode(String identifier) {
        this(identifier, null);
    }

    public TypeNode(String identifier, TypeNode supertype) {
        this.identifier = identifier;
        this.supertype = supertype;
    }

    @Override
    public String toString() {
        Stack<String> inheritance = new Stack<>();
        prependNameTo(inheritance);

        String ret = inheritance
                .stream()
                .collect(Collectors.joining("->"));

        return ret;
    }

    private void prependNameTo(Stack<String> inheritance) {
        inheritance.add(identifier);

        if (supertype != null) {
            supertype.prependNameTo(inheritance);
        }
    }

}
