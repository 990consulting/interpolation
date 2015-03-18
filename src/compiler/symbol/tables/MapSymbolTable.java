/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import com.google.common.reflect.TypeToken;
import compiler.symbol.symbols.InterpolableMemberSymbol;
import compiler.symbol.symbols.MemberSymbol;
import compiler.util.UnrecognizedIdentifierError;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/** * Created by dbborens on 3/3/15.
 */
public abstract class MapSymbolTable<T> implements InstantiableSymbolTable<T> {
    private final HashMap<String, MemberSymbol> requiredMembers;
    private final HashMap<String, Function<T, ?>> reserved;
    private final HashMap<String, InterpolableMemberSymbol> optionalMembers;
    private final List<String> interpolationSequence;

    private final TypeToken<T> type = new TypeToken<T>(getClass()) {};

    public MapSymbolTable() {
        reserved = resolveReserved();
        requiredMembers = resolveMembers();
        optionalMembers = resolveOptionalMembers();
        interpolationSequence = resolveInterpolationSequence();
    }

    protected abstract HashMap<String, MemberSymbol> resolveMembers();
    protected abstract HashMap<String, Function<T, ?>> resolveReserved();
    protected abstract HashMap<String, InterpolableMemberSymbol> resolveOptionalMembers();
    protected abstract List<String> resolveInterpolationSequence();

    public ResolvingSymbolTable getSymbolTable(String identifier) {
        if (!requiredMembers.containsKey(identifier)) {
            throw new UnrecognizedIdentifierError();
        }

        return requiredMembers.get(identifier).getSymbolTable();
    }

    public Stream<String> getReservedKeywords() {
        return reserved.keySet().stream();
    }

    public <U> Function<T, U> getReservedFunction(String keyword) {
        return (Function<T, U>) reserved.get(keyword);
    }

    public Class getInstanceClass() {
        return type.getRawType();
    }
}
