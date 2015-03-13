/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.InstantiableSymbolTable;

/**
 * Created by dbborens on 3/3/15.
 */
public class ListMemberResolver {

    private ListObjectNode objectNode;

    public ListMemberResolver(ListObjectNode objectNode) {
        this.objectNode = objectNode;
    }

    public InstantiableSymbolTable resolve(ASTValueNode value) {
        ClassSymbolTable classTable = objectNode.getSymbolTable();
        InstantiableSymbolTable ret = classTable.getSymbolTable(value);
        return ret;
    }
}
