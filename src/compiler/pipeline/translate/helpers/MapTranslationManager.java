/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.NestedContext;
import compiler.symbol.tables.MapSymbolTable;

/**
 * Created by dbborens on 3/2/15.
 */
public class MapTranslationManager {

    private MapTranslationVisitor visitor;
    private MapAssignmentLoaderFactory factory;

    public MapTranslationManager() {
        this(new MapTranslationVisitor(), new MapAssignmentLoaderFactory());
    }

    public MapTranslationManager(MapTranslationVisitor visitor,
                                 MapAssignmentLoaderFactory factory) {

        this.visitor = visitor;
        this.factory = factory;
    }

    public void init(TranslationCallback walker) {
        factory.init(walker);
    }

    public MapObjectNode translate(ASTValueNode root, MapSymbolTable symbolTable, NestedContext reserved) {
        MapAssignmentLoader loader = factory.build(symbolTable, reserved);
        return visitor.translate(root, loader);
    }
}
