/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.util.UnrecognizedIdentifierException;

import java.util.HashMap;

/**
 * Created by dbborens on 2/21/15.
 */
public class LocalContext implements Context {

    private HashMap<String, Symbol> members;

    public LocalContext() {
        members = new HashMap<>();
    }

    @Override
    public void put(String identifier, Symbol symbol) {
        members.put(identifier, symbol);
    }

    @Override
    public boolean has(String identifier) {
        return members.containsKey(identifier);
    }

    @Override
    public Symbol get(String identifier) throws UnrecognizedIdentifierException {
        if (!has(identifier)) {
            throw new UnrecognizedIdentifierException();
        }
        return members.get(identifier);
    }
}
