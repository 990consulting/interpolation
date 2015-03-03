/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.symbol.SymbolTable;

/**
 * Created by dbborens on 2/21/15.
 */
public class BlockWalker extends SlaveWalker<ASTBlockNode> {

    @Override
    public void walk(ASTBlockNode toWalk, SymbolTable symbolTable)  {
        toWalk.getChildren()
                .forEach(child -> acceptChild(child, symbolTable));
    }

    private void acceptChild(ASTValueNode child, SymbolTable symbolTable)  {
        walker.accept(child, symbolTable);
    }
}
