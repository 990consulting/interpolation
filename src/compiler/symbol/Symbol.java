/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.nodes.TypeNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.symbol.resolvers.Resolver;

/**
 * Created by dbborens on 2/18/15.
 */
public class Symbol {

    private String description;
    private Resolver resolver;
    private SymbolTable symbolTable;

    /**
     * @param description Textual description of the symbol, as it pertains to
     *                    the symbol table containing this symbol.
     *
     * @param resolver A function that can identify the narrowed type of the
     *                 symbol (if any) from the input node and return a symbol
     *                 table for that narrowed type. If the symbol is already
     *                 entirely narrow, the resolver will ignore the input node.
     */
    public Symbol(String description,
                  Resolver resolver) {

        this.description = description;
        this.resolver = resolver;
    }

    public String getDescription() {
        return description;
    }

    public TypeNode getType() {
        return resolver.getType();
    }

    public void resolve(ASTValueNode node) {
        symbolTable = resolver.apply(node);
    }

    public SymbolTable getSymbolTable() {
        if (symbolTable == null) {
            throw new IllegalStateException("Attempted to get symbol table for unresolved symbol");
        }
        return symbolTable;
    }
}
