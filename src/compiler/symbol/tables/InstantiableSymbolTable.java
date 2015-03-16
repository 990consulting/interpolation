/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import compiler.pipeline.build.Builder;
import compiler.pipeline.translate.nodes.ObjectNode;

/**
 * Created by dbborens on 3/11/15.
 */
public interface InstantiableSymbolTable<T> extends SymbolTable {

    public abstract Builder<? extends ObjectNode, T> getBuilder();

}
