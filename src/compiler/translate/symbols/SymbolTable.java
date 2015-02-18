/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.interpret.nodes.ASTReferenceNode;
import compiler.translate.nodes.TranslatorObjectNode;
import compiler.translate.nodes.TranslatorReferenceNode;
import compiler.translate.visitors.TranslationVisitor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/16/15.
 */
public abstract class SymbolTable {

    public void validate(ASTReferenceNode node) {
        if (isUnrecognized(node)) {
            String msg = Stream.of(
                    "Type '",
                    getType().toString(),
                    "' has no member '",
                    toString(),
                    ".'"
            ).collect(Collectors.joining());
            throw new IllegalArgumentException(msg);
        }
    }


    public abstract TranslationVisitor getVisitorFor(ASTReferenceNode reference);

    protected abstract TranslatorReferenceNode getType();

    public TranslatorObjectNode node() {
        return new TranslatorObjectNode(getType());
    }

    protected abstract boolean isUnrecognized(ASTReferenceNode node);
}
