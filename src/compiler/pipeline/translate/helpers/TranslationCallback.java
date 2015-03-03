/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.SymbolTable;

/**
 * Created by dbborens on 3/2/15.
 */
@FunctionalInterface
public interface TranslationCallback {
    public ObjectNode walk(ASTValueNode node, SymbolTable st);
}
