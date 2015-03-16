/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.NestedContext;
import compiler.symbol.tables.ListSymbolTable;

/**
 * Created by dbborens on 3/2/15.
 */
public class ListTranslationManager {

    private ListTranslationVisitor visitor;
    private ListValueLoaderFactory factory;

    public ListTranslationManager() {
        this(new ListTranslationVisitor(), new ListValueLoaderFactory());
    }

    public ListTranslationManager(ListTranslationVisitor visitor,
                                  ListValueLoaderFactory factory) {

        this.visitor = visitor;
        this.factory = factory;
    }

    public void init(TranslationCallback walker) {
        factory.init(walker);
    }

    public ListObjectNode translate(ASTValueNode root, ListSymbolTable symbolTable, NestedContext reserved) {
        ListValueLoader loader = factory.build(symbolTable, reserved);
        return visitor.translate(root, loader);
    }
}
