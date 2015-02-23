/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.symbol.SymbolTable;
import compiler.util.UserError;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by dbborens on 2/21/15.
 */
public class ReferenceWalker extends SlaveWalker<ASTReferenceNode> {

    @Override
    public void walk(ASTReferenceNode toWalk, SymbolTable symbolTable) throws UserError {
        if (toWalk.hasChild()) {
            throw new NotImplementedException();
        }
        symbolTable.initSymbolTable(toWalk);
    }
}
