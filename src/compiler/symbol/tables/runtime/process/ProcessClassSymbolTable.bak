/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.process;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.InstantiableSymbolTable;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/13/15.
 */
public class ProcessClassSymbolTable extends ClassSymbolTable {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> subclasses = new HashMap<>();
        ClassSymbol scatter = scatter();
        subclasses.put("scatter", scatter);
        return subclasses;
    }

    private ClassSymbol scatter() {
        Supplier<InstantiableSymbolTable> instance = () -> new ScatterSymbolTable();
        return new ClassSymbol(instance, "Places a specified number of agents" +
                ", eachsatisfying a specified description, in random, " +
                "unoccupied locations within a specified region of space.");
    }
}
