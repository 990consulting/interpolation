/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.NestedContext;
import compiler.pipeline.translate.nodes.PrimitiveNode;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.primitive.PrimitiveSymbolTable;

/**
 * Created by dbborens on 2/22/15.
 */
public class TranslateSlaveManager {

    private ListTranslationManager listManager;
    private MapTranslationManager mapManager;
//    private PrimitiveTranslationManager primitiveManager;

    public TranslateSlaveManager() {
        this(new ListTranslationManager(), new MapTranslationManager());
    }

    public TranslateSlaveManager(ListTranslationManager listManager,
                                 MapTranslationManager mapManager) {

        this.listManager = listManager;
        this.mapManager = mapManager;
    }

    public MapObjectNode translate(ASTValueNode root, MapSymbolTable symbolTable, NestedContext reserved) {
        return mapManager.translate(root, symbolTable, reserved);
    }

    public ListObjectNode translate(ASTValueNode root, ListSymbolTable symbolTable, NestedContext reserved) {
        return listManager.translate(root, symbolTable, reserved);
    }

    public void init(TranslationCallback walker) {
        mapManager.init(walker);
        listManager.init(walker);
    }

    public PrimitiveNode translate(ASTValueNode toTranslate, PrimitiveSymbolTable symbolTable) {
        if (!(toTranslate instanceof ASTPrimitiveNode)) {
            throw new IllegalStateException("AST node does not match symbol table");
        }

        return symbolTable.convert((ASTPrimitiveNode) toTranslate);
    }
}
