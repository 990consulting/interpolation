/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.ListSymbolTable;
import compiler.symbol.MapSymbolTable;
import compiler.symbol.ReservedContext;

/**
 * Created by dbborens on 2/22/15.
 */
public class TranslateSlaveManager {

    private ListTranslationManager listManager;
    private MapTranslationManager mapManager;

    public TranslateSlaveManager() {
        this(new ListTranslationManager(), new MapTranslationManager());
    }

    public TranslateSlaveManager(ListTranslationManager listManager,
                                 MapTranslationManager mapManager) {

        this.listManager = listManager;
        this.mapManager = mapManager;
    }

    public MapObjectNode translate(ASTValueNode root, MapSymbolTable symbolTable, ReservedContext reserved) {
        return mapManager.translate(root, symbolTable, reserved);
    }

    public ListObjectNode translate(ASTValueNode root, ListSymbolTable symbolTable, ReservedContext reserved) {
        return listManager.translate(root, symbolTable, reserved);
    }

    public void init(TranslationCallback walker) {
        mapManager.init(walker);
        listManager.init(walker);
    }
}
