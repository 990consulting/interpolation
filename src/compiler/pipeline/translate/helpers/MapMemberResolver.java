/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.InstantiableSymbolTable;

/**
 * Created by dbborens on 3/3/15.
 */
public class MapMemberResolver {

    private MapObjectNode objectNode;

    public MapMemberResolver(MapObjectNode objectNode) {
        this.objectNode = objectNode;
    }

    public InstantiableSymbolTable resolve(ASTAssignmentNode assignment) {

        String identifier = assignment.getReference().getIdentifier();
        ASTValueNode value = assignment.getValue();
        ClassSymbolTable classTable = objectNode.getSymbolTable(identifier);
        InstantiableSymbolTable ret = classTable.getSymbolTable(value);
        return ret;
    }
}
