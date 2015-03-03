/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.symbol.SymbolTable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by dbborens on 2/22/15.
 */
public class AssignmentWalker extends SlaveWalker<ASTAssignmentNode> {

    @Override
    public void walk(ASTAssignmentNode toWalk, SymbolTable symbolTable) {
        validate(toWalk);
        ASTValueNode value = toWalk.getValue();

        symbolTable.initSymbolTable(toWalk);
        SymbolTable childSymbolTable = symbolTable.getSymbolTable(toWalk);
        walker.accept(value, childSymbolTable);
    }

    private void validate(ASTAssignmentNode toWalk) {
        ASTReferenceNode reference = toWalk.getReference();
        if (reference.hasChild()) {
            throw new NotImplementedException();
        }
    }
}
