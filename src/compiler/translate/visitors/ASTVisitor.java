/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.visitors;

import compiler.interpret.nodes.ASTNode;
import compiler.translate.nodes.TranslatorNode;

/**
 * Created by dbborens on 2/18/15.
 */
public interface ASTVisitor<T extends ASTNode> {

    public TranslatorNode visit(T toVisit);
}
