/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.interpret.nodes.ASTReferenceNode;
import compiler.interpret.nodes.ASTStatementNode;
import compiler.interpret.nodes.ASTValueNode;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorObjectNode;
import compiler.translate.nodes.TranslatorReferenceNode;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A SymbolTable is a representation of all known symbols (words,
 * references) and the content to which they map. There is a
 * SymbolTable for every Nanoverse object that can be specified.
 *
 * The SymbolTable validates user input against expected parameters,
 * determining both whether the specified information is recognized
 * and well-defined, as well as whether all required information
 * has been provided.
 *
 * The details of this process depend on the type of input involved.
 *
 * Created by dbborens on 2/16/15.
 */
public abstract class SymbolTable {

    public abstract TranslatorNode translate(ASTValueNode content);
//    protected void validate(ASTReferenceNode node) {
//        if (isUnrecognized(node)) {
//            String msg = Stream.of(
//                    "Type '",
//                    getType().toString(),
//                    "' has no member '",
//                    toString(),
//                    ".'"
//            ).collect(Collectors.joining());
//            throw new IllegalArgumentException(msg);
//        }
//    }
//
//
//    /**
//     * Given a node representing all the content being assigned to this symbol table,
//     * return a TranslatorNode representing this object.
//     */
//    public abstract TranslatorNode translateMember(ASTValueNode content);
//
//    protected abstract TranslatorReferenceNode getType();
//
//    protected abstract boolean isUnrecognized(ASTReferenceNode node);
}
