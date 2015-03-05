/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.symbol.ListSymbolTable;
import compiler.symbol.ReservedContext;

/**
 * Created by dbborens on 3/2/15.
 */
public class ListValueLoaderFactory {

    private TranslationCallback callback;

    public void init(TranslationCallback callback) {
        this.callback = callback;
    }

    public ListValueLoader build(ListSymbolTable symbolTable, ReservedContext reserved) {
        ListObjectNode node = new ListObjectNode(symbolTable, reserved);
        ListValueLoader loader = new ListValueLoader(node, callback);
        return loader;
    }
}
