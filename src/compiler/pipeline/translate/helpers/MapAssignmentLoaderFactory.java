/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.context.ReservedContext;
import compiler.symbol.tables.MapSymbolTable;

/**
 * Created by dbborens on 3/2/15.
 */
public class MapAssignmentLoaderFactory {

    private TranslationCallback callback;

    public void init(TranslationCallback callback) {
        this.callback = callback;
    }

    public MapAssignmentLoader build(MapSymbolTable symbolTable, ReservedContext reserved) {
        MapObjectNode node = new MapObjectNode(symbolTable, reserved);
        MapAssignmentLoader loader = new MapAssignmentLoader(node, callback);
        return loader;
    }
}
