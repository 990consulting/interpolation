/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.pipeline.build.Builder;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/22/15.
 */
public class ListObjectNode<T extends List<?>> implements ObjectNode<T> {

    private ListSymbolTable symbolTable;
    private final LocalContextList local;
    private final NestedContext reserved;

    public ListObjectNode(ListSymbolTable symbolTable, NestedContext reserved) {
        this(symbolTable, new LocalContextList(), reserved);
    }

    public ListObjectNode(ListSymbolTable symbolTable, LocalContextList local, NestedContext reserved) {
        this.symbolTable = symbolTable;
        this.local = local;
        this.reserved = reserved;
    }

    public Stream<ObjectNode> getMemberStream() {
        return local.getMembers();
    }

    public ObjectNode getMember(int index) {
        if (index >= size()) {
            throw new IllegalArgumentException("List context member index out of bounds.");
        }
        return local.get(index);
    }

    public void loadMember(ObjectNode value) {
        local.loadMember(value);
    }

    public int size() {
        return local.size();
    }

    public ResolvingSymbolTable getSymbolTable() {
        return symbolTable;
    }

    public NestedContext getReserved() {
        return reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListObjectNode that = (ListObjectNode) o;

        if (!local.equals(that.local)) return false;
        if (!symbolTable.equals(that.symbolTable)) return false;

        return true;
    }

    @Override
    public void instantiate(Consumer<T> callback) {
        Builder builder = symbolTable.getBuilder();
        builder.visit(this, callback);
    }
}
