/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.util.IllegalAssignmentException;
import compiler.util.UnrecognizedIdentifierException;

import java.util.HashMap;

/**
 * Created by dbborens on 2/21/15.
 */
public class AbstractNestedContext implements Context {

    public AbstractNestedContext parent;

    private HashMap<String, Symbol> members;

    public AbstractNestedContext() {
        this(null);
    }

    public AbstractNestedContext(AbstractNestedContext parent) {
        this.parent = parent;
        members = new HashMap<>();
    }

    @Override
    public void put(String identifier, Symbol symbol) throws IllegalAssignmentException {
        members.put(identifier, symbol);
    }

    @Override
    public boolean has(String identifier) {
        if (members.containsKey(identifier)) {
            return true;
        }

        if (parent != null && parent.has(identifier)) {
            return true;
        }

        return false;
    }

    @Override
    public Symbol get(String identifier) throws UnrecognizedIdentifierException {
        if (!has(identifier)) {
            throw new UnrecognizedIdentifierException();
        }

        if (members.containsKey(identifier)) {
            return members.get(identifier);
        }

        return parent.get(identifier);
    }
}
