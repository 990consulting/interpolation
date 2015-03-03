/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.symbol.SymbolTable;

import java.util.function.BiConsumer;

/**
 * Abstract superclass for initialization walkers that provide
 * specific instructions for walking a particular subclass of
 * AST node before passing it back to the master initialization
 * walker.
 *
 * Created by dbborens on 2/21/15.
 */
public abstract class SlaveWalker<T extends ASTNode> {

    // Master initialization walker's walkChildren method
    protected BiConsumer<ASTValueNode, SymbolTable> walker;

    public void init(BiConsumer<ASTValueNode, SymbolTable> walker) {
        this.walker = walker;
    }

    public abstract void walk(T toWalk, SymbolTable symbolTable) ;

}
