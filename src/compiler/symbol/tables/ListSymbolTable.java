/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import compiler.pipeline.build.Builder;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;

import java.util.List;

/**
 * Created by dbborens on 3/4/15.
 */
public class ListSymbolTable<T> implements InstantiableSymbolTable<List<T>>,  ResolvingSymbolTable {

    private final ClassSymbolTable classSymbolTable;

    public ListSymbolTable (ClassSymbolTable classSymbolTable) {
        this.classSymbolTable = classSymbolTable;
    }

    @Override
    public InstantiableSymbolTable getSymbolTable(ASTValueNode value) {
        return classSymbolTable.getSymbolTable(value);
    }

    @Override
    public Builder<ListObjectNode, List<T>> getBuilder() {
        return null;
    }

    @Override
    public Class getInstanceClass() {
        return classSymbolTable.getBroadClass();
    }
}
