/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.symbols;

import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.symbol.tables.InstantiableSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/16/15.
 */
public class InterpolableMemberSymbol extends MemberSymbol {
    private List<String> defaultSequence;

    public InterpolableMemberSymbol(ResolvingSymbolTable symbolTable, Stream<String> defaultSequence, String description) {
        super(symbolTable, description);
        this.defaultSequence = defaultSequence.collect(Collectors.toList());
    }

    public Stream<InstantiableSymbolTable> getDefaultSequence() {
        return defaultSequence.stream()
                .map(id -> new ASTReferenceNode(id))
                .map(reference ->
                        symbolTable.getSymbolTable(reference));
    }
}
