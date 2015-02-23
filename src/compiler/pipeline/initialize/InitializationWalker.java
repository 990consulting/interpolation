/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize;

import compiler.pipeline.initialize.helpers.SlaveManager;
import compiler.pipeline.interpret.nodes.*;
import compiler.symbol.SymbolTable;
import compiler.symbol.tables.RootSymbolTable;
import compiler.util.UserError;

/**
 * Breadth-first search of the abstract syntax tree, which initializes
 * symbol tables with any reserved, instance or user-specified identifiers.
 *
 * Process, starting with the root symbol table and node:
 *
 *
 *     (0) Before starting, the symbol table will have been initialized with
 *         an inherited user and reserved symbol table, as well as a member
 *         symbol table.
 *
 *     (1) Visit each child node. If any are definitions, define the symbol
 *         in the "user" context.
 *
 *     (2) Visit each child node. For all nodes, initialize a symbol table
 *         for the node based on its narrowest type. Then recur on the node,
 *         providing as an argument its symbol table.
 *
 * Created by dbborens on 2/21/15.
 */
public class InitializationWalker {

    private SlaveManager slaveManager;

    public InitializationWalker() {
        slaveManager = new SlaveManager();
    }

    public InitializationWalker(SlaveManager slaveManager) {
        this.slaveManager = slaveManager;
    }

    public RootSymbolTable walk(ASTRootNode root) throws UserError {
        RootSymbolTable ret = new RootSymbolTable();
        walkChildren(root, ret);
        return ret;
    }

    protected void walkChildren(ASTValueNode toWalk, SymbolTable symbolTable) throws UserError {
        // Walk the child list, loading any user-defined values.
        loadUserDefined(toWalk, symbolTable);

        if (toWalk instanceof ASTAssignmentNode) {
            slaveManager.walk((ASTAssignmentNode) toWalk, symbolTable);

        } else if (toWalk instanceof ASTBlockNode) {
            slaveManager.walk((ASTBlockNode) toWalk, symbolTable);

        } else if (toWalk instanceof ASTDefinitionNode) {
            slaveManager.walk((ASTDefinitionNode) toWalk, symbolTable);

        } else if (toWalk instanceof ASTReferenceNode) {
            slaveManager.walk((ASTReferenceNode) toWalk, symbolTable);

        } else if (toWalk instanceof ASTPrimitiveNode) {
            // Nothing to initialize for primitive nodes
            return;

        } else {
            throw new IllegalStateException("Unrecognized AST node");
        }
    }


    private void loadUserDefined(ASTNode toWalk, SymbolTable symbolTable) {
        // User-defined context not yet implemented
    }

}
