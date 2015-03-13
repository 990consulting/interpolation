/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.util.UnrecognizedIdentifierError;

import java.util.HashMap;

/**
 * Created by dbborens on 3/3/15.
 */
public abstract class InstanceSymbolTable<T> implements InstantiableSymbolTable<T> {

    private HashMap<String, MemberSymbol> members;

    public InstanceSymbolTable() {
        members = resolveMembers();
    }

    protected abstract HashMap<String, MemberSymbol> resolveMembers();

    public ClassSymbolTable getSymbolTable(String identifier) {
        if (!members.containsKey(identifier)) {
            throw new UnrecognizedIdentifierError();
        }

        return members.get(identifier).getSymbolTable();
    }
}
