/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.SymbolTable;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/22/15.
 */
public class ListObjectNode extends ObjectNode {
    private final ContextList list;

    public ListObjectNode(SymbolTable symbolTable) {
        this(symbolTable, new ContextList());
    }

    public ListObjectNode(SymbolTable symbolTable, ContextList list) {
        super(symbolTable);
        this.list = list;
    }

    public Stream<ObjectNode> getMemberStream() {
        return null;
    }

    public ObjectNode getMember(int index) {
        return null;
    }

    public void loadMember(ObjectNode value) {

    }

    public int size() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListObjectNode that = (ListObjectNode) o;

        if (!list.equals(that.list)) return false;
        if (!symbolTable.equals(that.symbolTable)) return false;

        return true;
    }
}
