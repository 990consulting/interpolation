/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.MapSymbolTable;
import compiler.symbol.SymbolTable;

import java.util.stream.Stream;

/**
 * MapObjectNode represents a Java object whose members
 * have definite names. This is generally everything except
 * a Collection.
 *
 * Created by dbborens on 2/22/15.
 */
public class MapObjectNode extends ObjectNode<MapSymbolTable> {

    private final ContextMap map;

    public MapObjectNode(MapSymbolTable symbolTable) {
        this(symbolTable, new ContextMap());
    }

    public MapObjectNode(MapSymbolTable symbolTable, ContextMap map) {
        super(symbolTable);
        this.map = map;
    }

    public Stream<String> getDefinedMemberNames() {
        return map.getMemberNames();
    }

    public ObjectNode getMember(String name) {
        return map.getMember(name);
    }

    public void loadMember(String identifier, ObjectNode value) {
        map.loadMember(identifier, value);
    }

    public SymbolTable getSymbolTableFor(String name) {
        return symbolTable.getSymbolTable(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapObjectNode that = (MapObjectNode) o;

        if (!map.equals(that.map)) return false;
        if (!symbolTable.equals(that.symbolTable)) return false;

        return true;
    }
}
