/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import compiler.nodes.TypeNode;
import compiler.symbol.LocalContext;
import compiler.symbol.MapSymbolTable;
import compiler.symbol.ReservedContext;

/**
 * Created by dbborens on 2/21/15.
 */
public class RootSymbolTable extends MapSymbolTable {

    public RootSymbolTable() {
        super(null);
    }

    @Override
    protected LocalContext resolveLocalContext() {
        LocalContext ret = new LocalContext();
        return ret;
    }

    @Override
    protected ReservedContext resolveReservedContext(ReservedContext parent) {
        ReservedContext ret = new ReservedContext(parent);
        return ret;
    }

    @Override
    protected TypeNode resolveType() {
        return new TypeNode("root");
    }
}
