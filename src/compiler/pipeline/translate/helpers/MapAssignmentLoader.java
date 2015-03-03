/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.SymbolTable;

/**
 * Created by dbborens on 3/2/15.
 */
public class MapAssignmentLoader {

    private MapObjectNode node;
    private TranslationCallback callback;
    private boolean finished;

    public MapAssignmentLoader(MapObjectNode node, TranslationCallback callback) {
        this.callback = callback;
        this.node = node;
        finished = false;
    }

    public void loadAssignment(ASTAssignmentNode toTranslate) {
        if (finished) {
            throw new IllegalStateException("Attempting to add to finished node");
        }

        String identifier = toTranslate.getReference().getIdentifier();
        SymbolTable childSt = node.getSymbolTableFor(identifier);
        ASTValueNode childValue = toTranslate.getValue();
        ObjectNode childNode = callback.walk(childValue, childSt);
        node.loadMember(identifier, childNode);
    }

    public MapObjectNode finish()  {
        finished = true;
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapAssignmentLoader that = (MapAssignmentLoader) o;

        if (!callback.equals(that.callback)) return false;
        if (!node.equals(that.node)) return false;

        return true;
    }
}
