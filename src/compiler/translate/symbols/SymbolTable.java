/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.interpret.nodes.ReferenceNode;
import compiler.interpret.nodes.ValueNode;
import compiler.translate.TranslationVisitor;
import compiler.translate.nodes.TranslatorReferenceNode;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/16/15.
 */
public abstract class SymbolTable {

    public void validate(ReferenceNode node) {
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


    public abstract TranslationVisitor getVisitorFor(ValueNode node);

    public abstract TranslatorReferenceNode getType();

    protected abstract boolean isUnrecognized(ReferenceNode node);
}
