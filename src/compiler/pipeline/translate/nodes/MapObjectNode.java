/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.pipeline.build.Builder;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;
import compiler.util.IllegalAssignmentError;

import java.util.function.Consumer;
import java.util.stream.Stream;


/**
 * MapObjectNode represents a Java object whose members
 * have definite names. This is generally everything except
 * a Collection.
 *
 * Created by dbborens on 2/22/15.
 */
public class MapObjectNode implements ObjectNode {

    private final LocalContextMap local;
    private final NestedContext reserved;

    private final MapSymbolTable symbolTable;

    public MapObjectNode(MapSymbolTable symbolTable, NestedContext reserved) {
        this(symbolTable, reserved, new LocalContextMap());
    }

    public MapObjectNode(MapSymbolTable symbolTable, NestedContext reserved, LocalContextMap local) {
        this.symbolTable = symbolTable;
        this.reserved = reserved;
        this.local = local;
    }

    public void loadMember(String identifier, Resolvable value) {
        if (reserved.has(identifier)) {
            throw new IllegalAssignmentError();
        }
        local.loadMember(identifier, value);
    }

    public Stream<String> getMemberIdentifiers() {
        return local.getMemberIdentifiers();
    }

    public Resolvable getMember(String identifier) {
        if (reserved.has(identifier)) {
            return reserved.get(identifier);
        }

        return local.getMember(identifier);
    }

    public ResolvingSymbolTable getSymbolTable(String identifier) {
        if (reserved.has(identifier)) {
            throw new IllegalAssignmentError("Attempting to assign to reserved keyword " + identifier);
        }

        return symbolTable.getSymbolTable(identifier);
    }

    public NestedContext getReserved() {
        return reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapObjectNode that = (MapObjectNode) o;

        if (!local.equals(that.local)) return false;
        if (!symbolTable.equals(that.symbolTable)) return false;

        return true;
    }

    @Override
    public void instantiate(Consumer callback) {
        Builder builder = symbolTable.getBuilder();
        builder.visit(this, callback);
    }

    @Override
    public Class getInstanceClass() {
        return symbolTable.getInstanceClass();
    }

}
