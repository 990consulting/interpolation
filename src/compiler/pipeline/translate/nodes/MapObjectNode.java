/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.context.ReservedContext;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.util.IllegalAssignmentError;


/**
 * MapObjectNode represents a Java object whose members
 * have definite names. This is generally everything except
 * a Collection.
 *
 * Created by dbborens on 2/22/15.
 */
public class MapObjectNode<T> implements ObjectNode {

    private final LocalContextMap local;
    private final ReservedContext reserved;

    private final MapSymbolTable<T> symbolTable;

    public MapObjectNode(MapSymbolTable<T> symbolTable, ReservedContext reserved) {
        this(symbolTable, reserved, new LocalContextMap());
    }

    public MapObjectNode(MapSymbolTable<T> symbolTable, ReservedContext reserved, LocalContextMap local) {
        this.symbolTable = symbolTable;
        this.reserved = reserved;
        this.local = local;
    }

    public void loadMember(String identifier, ObjectNode value) {
        local.loadMember(identifier, value);
    }

    public ClassSymbolTable getSymbolTable(String identifier) {
        if (reserved.has(identifier)) {
            throw new IllegalAssignmentError("Attempting to assign to reserved keyword " + identifier);
        }

        return symbolTable.getSymbolTable(identifier);
    }

    public ReservedContext getReserved() {
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
}
