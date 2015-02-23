/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.resolvers;

import compiler.nodes.TypeNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.symbol.SymbolTable;

import java.util.function.Function;

/**
 * Created by dbborens on 2/22/15.
 */
public interface Resolver extends Function<ASTNode, SymbolTable> {

    public TypeNode getType();
}
