/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.symbol.ListSymbolTable;

/**
 * ListTranslationVisitor expects a root node containing
 * zero or more members, each of which can be resolved to
 * a member of the expected type for this list.
 *
 * Initially, the ListObjectNode to return is constructed.
 *
 * The symbol table for each child node is then retrieved,
 * after which the child node is translated with its symbol
 * table by passing them back to the master translation
 * visitor.
 *
 * The translated child node is then appended to the return
 * object.
 *
 * Finally, when all children have been visited, the return
 * ListObjectNode is returned.
 *
 * Created by dbborens on 2/22/15.
 */
public class ListTranslationVisitor {

    public ListObjectNode translate(ASTValueNode root, ListSymbolTable symbolTable) {
        return null;
    }

    public void init(TranslationCallback walker) {
    }
}
