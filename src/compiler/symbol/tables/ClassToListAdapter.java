/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import compiler.pipeline.interpret.nodes.ASTValueNode;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/13/15.
 */
public class ClassToListAdapter<T> implements ResolvingSymbolTable {

    private final Supplier<ListSymbolTable<T>> contents;

    public ClassToListAdapter(Supplier<ListSymbolTable<T>> contents) {
        this.contents = contents;
    }

    @Override
    public InstantiableSymbolTable<List<T>> getSymbolTable(ASTValueNode value) {
        return contents.get();
    }
}