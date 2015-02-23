/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.nodes.TypeNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.util.UserError;

/**
 * Created by dbborens on 2/21/15.
 */
public interface SymbolTable<T extends ASTValueNode> {

    /**
     * Returns the data type represented by this subtree. The supertype
     * hierarchy, if any, is represented as a series of child type objects.
     *
     * @return data type described by this symbol table.
     */
    public TypeNode getType();

    /**
     * Construct a symbol table for a particular child node. The particular
     * symbol table provided will be the one corresponding to the narrowest
     * possible interpretation of the child's type.
     */
    public void initSymbolTable(T content) throws UserError;

    /**
     * Returns the symbol table. Should blow up if called before
     * symbol table is initialized.
     *
     * @param content The node for which a symbol table is required.
     * @return The symbol table required to translate this node.
     */
    public SymbolTable getSymbolTable(T content) throws UserError;
}
