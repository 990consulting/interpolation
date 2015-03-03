/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.nodes.TypeNode;
import compiler.symbol.SymbolTable;

/**
 * Created by dbborens on 2/22/15.
 */
public abstract class ObjectNode<T extends SymbolTable> implements TranslatedNode {

    protected final T symbolTable;

    public ObjectNode(T symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public TypeNode getType() {
        return symbolTable.getType();
    }
}
