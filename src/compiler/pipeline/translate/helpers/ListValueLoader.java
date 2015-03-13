/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.tables.InstantiableSymbolTable;

/**
 * Created by dbborens on 3/2/15.
 */
public class ListValueLoader {

    private ListObjectNode node;
    private TranslationCallback callback;
    private ListMemberResolver resolver;
    private boolean finished;

    public ListValueLoader(ListObjectNode node, TranslationCallback callback) {
        this.callback = callback;
        this.node = node;
        resolver = new ListMemberResolver(node);
        finished = false;
    }

    public void loadAssignment(ASTValueNode toTranslate) {
        if (finished) {
            throw new IllegalStateException("Attempting to add to finished node");
        }
        InstantiableSymbolTable childSt = resolver.resolve(toTranslate);
        ObjectNode childNode = callback.walk(toTranslate, childSt, node.getReserved());
        node.loadMember(childNode);
    }

    public ListObjectNode finish()  {
        finished = true;
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListValueLoader that = (ListValueLoader) o;

        if (!callback.equals(that.callback)) return false;
        if (!node.equals(that.node)) return false;

        return true;
    }
}
