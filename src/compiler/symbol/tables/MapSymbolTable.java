/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import compiler.symbol.symbols.MemberSymbol;
import compiler.util.UnrecognizedIdentifierError;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Stream;

/** * Created by dbborens on 3/3/15.
 */
public abstract class MapSymbolTable<T> implements InstantiableSymbolTable<T> {
    private final HashMap<String, MemberSymbol> members;
    private final HashMap<String, Function<T, ?>> reserved;

    public MapSymbolTable() {
        reserved = resolveReserved();
        members = resolveMembers();

    }

    protected abstract HashMap<String, MemberSymbol> resolveMembers();
    protected abstract HashMap<String, Function<T, ?>> resolveReserved();

    public ResolvingSymbolTable getSymbolTable(String identifier) {
        if (!members.containsKey(identifier)) {
            throw new UnrecognizedIdentifierError();
        }

        return members.get(identifier).getSymbolTable();
    }

    public Stream<String> getReservedKeywords() {
        return reserved.keySet().stream();
    }

    public <U> Function<T, U> getReservedFunction(String keyword) {
        return (Function<T, U>) reserved.get(keyword);
    }
}
