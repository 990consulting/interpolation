/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.symbols;

import compiler.symbol.tables.ClassSymbolTable;

/**
 * A member symbol reports back the expected class for
 * a particular symbol in an instance.
 *
 * Created by dbborens on 3/3/15.
 */
public class MemberSymbol {

    private ClassSymbolTable symbolTable;
    private String description;

    public MemberSymbol(ClassSymbolTable symbolTable, String description) {
        this.symbolTable = symbolTable;
        this.description = description;
    }

    public ClassSymbolTable getSymbolTable() {
        return symbolTable;
    }

    public String getDescription() {
        return description;
    }
}
