/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.nodes;

import compiler.interpret.nodes.ASTReferenceNode;
import compiler.translate.ReferenceNameConverter;

/**
 * Created by dbborens on 2/18/15.
 */
public class TranslatorObjectNodeBuilder {

    private TranslatorObjectNode parent;
    private ReferenceNameConverter converter;

    boolean built;

    public TranslatorObjectNodeBuilder(TranslatorReferenceNode parentType) {
        built = false;
        parent = new TranslatorObjectNode(parentType);
        converter = new ReferenceNameConverter();
    }

    public TranslatorObjectNode build() {
        if (built) {
            throw new IllegalStateException("Object initialized twice");
        }

        built = true;

        return parent;
    }

    public void load(ASTReferenceNode memberReference, TranslatorNode member) {
        String name = converter.convert(memberReference);
        if (parent.hasMember(name)) {
            throw new IllegalArgumentException("Member '" + name + "' initialized twice in object '" + parent.getType() + ".'");
        }

        parent.loadMember(memberReference, member);
    }
}
