/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.util.SyntaxError;

/**
 * MapTranslationVisitor expects a root node containing
 * zero or assignments, the reference of each of which
 * can be resolved to a member identifier in the symbol
 * table.
 *
 * Initially, the MapObjectNode to return is constructed.
 *
 * The symbol table for each child node is then retrieved,
 * after which the child node is translated with its symbol
 * table by passing them back to the master translation
 * visitor.
 *
 * The translated child node is then mapped by its reference
 * identifier into the return object. (Nested reference
 * identifiers are not permitted, although nested value
 * references are permitted.)
 *
 * Finally, when all children have been visited, the return
 * MapObjectNode is returned.
 *
 * Created by dbborens on 2/22/15.
 */
public class MapTranslationVisitor {

    public MapObjectNode translate(ASTValueNode root, MapAssignmentLoader loader) {
        if (root instanceof ASTReferenceNode) {
            return loader.finish();
        }

        if (root instanceof ASTAssignmentNode) {
            loader.loadAssignment((ASTAssignmentNode) root);
            return loader.finish();
        }

        if (root instanceof ASTBlockNode) {
            return doBlockCase((ASTBlockNode) root, loader);
        }

        throw new SyntaxError("Illegal input format in named object specification.");
    }

    private MapObjectNode doBlockCase(ASTBlockNode root, MapAssignmentLoader loader) {
        root.getChildren().forEach(child -> {
            if (!(child instanceof ASTAssignmentNode)) {
                throw new SyntaxError("Expected assignment in attribute specification block.");
            }

            loader.loadAssignment((ASTAssignmentNode) child);
        });
        return loader.finish();
    }

}
