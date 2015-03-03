/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.nodes.TypeNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;

/**
 * Created by dbborens on 2/22/15.
 */
public class ListSymbolTable implements SymbolTable {
    @Override
    public TypeNode getType() {
        return null;
    }

    @Override
    public void initSymbolTable(ASTValueNode content)  {

    }

    @Override
    public SymbolTable getSymbolTable(ASTValueNode content)  {
        return null;
    }
}
