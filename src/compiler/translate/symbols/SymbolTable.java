/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.interpret.nodes.ASTNode;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorReferenceNode;

/**
 * A SymbolTable is a representation of all known symbols (words,
 * references) and the content to which they map. There is a
 * SymbolTable for every Nanoverse object that can be specified.
 *
 * The SymbolTable validates user input against expected parameters,
 * determining both whether the specified information is recognized
 * and well-defined, as well as whether all required information
 * has been provided.
 *
 * The details of this process depend on the type of input involved.
 *
 * Created by dbborens on 2/16/15.
 */
public abstract class SymbolTable<T extends ASTNode> {

    public abstract TranslatorNode translate(T content);
    protected TranslatorReferenceNode type;

    public SymbolTable(TranslatorReferenceNode type) {
        this.type = type;
    }

    public TranslatorReferenceNode getType() {
        return type;
    }
}
