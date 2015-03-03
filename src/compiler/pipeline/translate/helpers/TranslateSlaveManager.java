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

/**
 * Created by dbborens on 2/22/15.
 */
public class TranslateSlaveManager {

    private ListTranslationVisitor listVisitor;
    private MapTranslationManager mapManager;

    public TranslateSlaveManager() {
        this(new ListTranslationVisitor(), new MapTranslationManager());
    }

    public TranslateSlaveManager(ListTranslationVisitor listVisitor,
                                 MapTranslationManager mapManager) {

        this.listVisitor = listVisitor;
        this.mapManager = mapManager;
    }

    public MapObjectNode translate(ASTValueNode root, MapSymbolTable symbolTable) {
        return mapManager.translate(root, symbolTable);
    }

    public ListObjectNode translate(ASTValueNode root, ListSymbolTable symbolTable) {
        return listVisitor.translate(root, symbolTable);
    }

    public void init(TranslationCallback walker) {
        mapManager.init(walker);
        listVisitor.init(walker);
    }
}
