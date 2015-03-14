/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.process;

import compiler.pipeline.build.builders.Builder;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import runtime.process.agent.Scatter;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Created by dbborens on 3/13/15.
 */
public class ScatterSymbolTable extends MapSymbolTable<Scatter> {

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        return null;
    }

    @Override
    public Builder<Scatter> getBuilder() {
        return null;
    }

    @Override
    protected HashMap<String, Function<Scatter, ?>> resolveReserved() {
        return null;
    }
}
