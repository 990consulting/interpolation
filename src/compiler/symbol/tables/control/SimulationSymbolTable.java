/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.control;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import runtime.control.Simulation;

import java.util.HashMap;

/**
 * Created by dbborens on 3/13/15.
 */
public class SimulationSymbolTable extends MapSymbolTable<Simulation> {

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap members = new HashMap<>();
        members.put("initially", null);
        return members;
    }

    @Override
    public Simulation instantiate() {
        return null;
    }
}
