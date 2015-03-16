/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.util.UnrecognizedIdentifierError;

import java.util.HashMap;

/**
 * Represents reserved keywords. The current implementation (3/3/15) is a stub:
 * rather than being a map of keywords to resolvers, it is just a set of
 * keywords. Once I finish the translate stage, I will add the additional
 * functionality necessary to resolve keywords.
 *
 * Created by dbborens on 3/3/15.
 */
public class NestedContext {
    public NestedContext parent;

    private HashMap<String, NestedContextSymbol> members;

    public NestedContext() {
        this(null);
    }
    public NestedContext(NestedContext parent) {
        this.parent = parent;
        members = new HashMap<>();
    }

    public boolean has(String identifier) {
        if (members.containsKey(identifier)) {
            return true;
        }

        if (parent != null && parent.has(identifier)) {
            return true;
        }

        return false;
    }

    public void put(String identifier, NestedContextSymbol symbol) {
        members.put(identifier, symbol);
    }

    public NestedContextSymbol get(String identifier) {
        if (members.containsKey(identifier)) {
            return members.get(identifier);
        }

        else if (parent != null && parent.has(identifier)) {
            return parent.get(identifier);
        }

        throw new UnrecognizedIdentifierError();
    }

}
