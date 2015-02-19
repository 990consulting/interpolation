/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.visitors;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTReferenceNode;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorObjectNodeBuilder;
import compiler.translate.symbols.SymbolTable;

/**
 * Created by dbborens on 2/18/15.
 */
public class ASTObjAssignmentVisitor {

    private TranslatorObjectNodeBuilder builder;
    private SymbolTable symbolTable;

    public ASTObjAssignmentVisitor(SymbolTable symbolTable, TranslatorObjectNodeBuilder builder) {
        this.symbolTable = symbolTable;
        this.builder = builder;
    }

    public void visit(ASTAssignmentNode toVisit) {
        TranslatorNode result = symbolTable.translate(toVisit);
        ASTReferenceNode reference = toVisit.getReference();
        builder.load(reference, result);
    }
}
