/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import java.util.function.Supplier;

/**
 * A class symbol provides a symbol table for a specific subclass.
 * Created by dbborens on 3/3/15.
 */
public class ClassSymbol {

    private Supplier<InstanceSymbolTable> symbolTableSupplier;
    private String description;

    public ClassSymbol(Supplier<InstanceSymbolTable> symbolTableSupplier, String description) {
        this.symbolTableSupplier = symbolTableSupplier;
        this.description = description;
    }

    public InstanceSymbolTable getSymbolTable() {
        return symbolTableSupplier.get();
    }

    public String getDescription() {
        return description;
    }
}
