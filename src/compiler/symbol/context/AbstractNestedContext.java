/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.context;

import java.util.HashSet;

/**
 * Represents reserved keywords. The current implementation (3/3/15) is a stub:
 * rather than being a map of keywords to resolvers, it is just a set of
 * keywords. Once I finish the translate stage, I will add the additional
 * functionality necessary to resolve keywords.
 *
 * Created by dbborens on 3/3/15.
 */
public class AbstractNestedContext {
    public AbstractNestedContext parent;

    private HashSet<String> members;

    public AbstractNestedContext() {
        this(null);
    }
    public AbstractNestedContext(AbstractNestedContext parent) {
        this.parent = parent;
        members = new HashSet<>();
    }

    public boolean has(String identifier) {
        if (members.contains(identifier)) {
            return true;
        }

        if (parent != null && parent.has(identifier)) {
            return true;
        }

        return false;
    }

    public void put(String identifier) {
        members.add(identifier);
    }

}
