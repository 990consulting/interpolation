/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate;

import compiler.interpret.nodes.ASTNode;
import compiler.translate.nodes.TranslatorNode;

/**
 * Created by dbborens on 2/17/15.
 */
public interface TranslationVisitor<T extends ASTNode> {

    public TranslatorNode visit(T toVisit);
}
