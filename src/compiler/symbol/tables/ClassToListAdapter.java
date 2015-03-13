/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import compiler.pipeline.interpret.nodes.ASTValueNode;

/**
 * Created by dbborens on 3/13/15.
 */
public class ClassToListAdapter<T> implements ResolvingSymbolTable {

    private final ListSymbolTable<T> contents;

    public ClassToListAdapter(ListSymbolTable<T> contents) {
        this.contents = contents;
    }

    @Override
    public InstantiableSymbolTable<T> getSymbolTable(ASTValueNode value) {
        return contents;
    }
}