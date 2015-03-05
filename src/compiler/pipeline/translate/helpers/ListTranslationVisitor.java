/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
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
public class ListTranslationVisitor {

    public ListObjectNode translate(ASTValueNode root, ListValueLoader loader) {

        // If the AST node is a reference (=leaf), then just build the empty
        // object node and return it.
        if (root instanceof ASTReferenceNode) {
            return loader.finish();
        }

        // If the AST node is an assignment, then the contents represent a
        // single list member. Translate it, add it to the list, and return.
        if (root instanceof ASTAssignmentNode) {
            return doAssignmentCase((ASTAssignmentNode) root, loader);
        }

        // If the AST node is a block, then each child is a list member.
        // Translate each one and add it to the list, then return.
        else if (root instanceof ASTBlockNode) {
            return doBlockCase((ASTBlockNode) root, loader);
        }

        throw new SyntaxError("Illegal input format in named object specification.");
    }

    private ListObjectNode doAssignmentCase(ASTAssignmentNode root, ListValueLoader loader) {
        ASTValueNode value = root.getValue();
        loader.loadAssignment(value);
        return loader.finish();
    }

    private ListObjectNode doBlockCase(ASTBlockNode root, ListValueLoader loader) {
        root.getChildren().forEach(child -> loader.loadAssignment(child));
        return loader.finish();
    }

}
