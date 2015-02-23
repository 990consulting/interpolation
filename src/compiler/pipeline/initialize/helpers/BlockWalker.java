/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.symbol.SymbolTable;

/**
 * Created by dbborens on 2/21/15.
 */
public class BlockWalker extends SlaveWalker<ASTBlockNode> {

    @Override
    public void walk(ASTBlockNode toWalk, SymbolTable symbolTable) {
        toWalk.getChildren()
                .forEach(child ->
                        walker.accept(child, symbolTable));
    }
}
