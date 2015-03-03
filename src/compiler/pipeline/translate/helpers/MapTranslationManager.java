/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.MapSymbolTable;

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

    public MapObjectNode translate(ASTValueNode root, MapSymbolTable symbolTable) {
        MapAssignmentLoader loader = factory.build(symbolTable);
        return visitor.translate(root, loader);
    }
}
