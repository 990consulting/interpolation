/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.helpers.TranslateSlaveManager;
import compiler.pipeline.translate.helpers.TranslationCallback;
import compiler.pipeline.translate.nodes.NestedContext;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.SymbolTable;
import compiler.symbol.tables.primitive.PrimitiveSymbolTable;

/**
 * Depth-first search of the abstract syntax tree, which translates
 * each node into a roughly one-to-one correspondence with the
 * prototype for the Java objects into which the simulation will be
 * compiled. These translations are not necessarily complete; default
 * values will be inferred, if possible, in the next stage of the pipeline.
 *
 * Process, starting with the root symbol table and node:
 *
 *    (0) Before starting, each symbol in the symbol table corresponding to
 *        a node in the abstract syntax tree will have been resolved to the
 *        narrowest possible symbol table.
 *
 *    (1) For each child node, retrieve its symbol table, and then recursively
 *        translate it into an ObjectNode.
 *
 *    (2) Load each returned ObjectNode as members of the local ObjectNode.
 *
 * Created by dbborens on 2/22/15.
 */
public class TranslationVisitor {

    private TranslateSlaveManager manager;

    public TranslationVisitor(TranslateSlaveManager manager) {
        this.manager = manager;
        TranslationCallback walker = (node, st, reserved) -> translate(node, st, reserved);
        manager.init(walker);
    }

    public TranslationVisitor() {
        this (new TranslateSlaveManager());
    }

    public ObjectNode translate(ASTValueNode toTranslate, SymbolTable symbolTable, NestedContext reserved) {
        if (symbolTable instanceof ListSymbolTable) {
            return manager.translate(toTranslate, (ListSymbolTable) symbolTable, reserved);
        } else if (symbolTable instanceof MapSymbolTable) {
            return manager.translate(toTranslate, (MapSymbolTable) symbolTable, reserved);
        } else if (symbolTable instanceof PrimitiveSymbolTable) {
            return manager.translate(toTranslate, (PrimitiveSymbolTable) symbolTable);
        } else {
            throw new IllegalArgumentException("Unexpected symbol table class");
        }
    }
}
