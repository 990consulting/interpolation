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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        if (isMutation(toVisit)) {
            throw new NotImplementedException();
        }

        ASTReferenceNode reference = toVisit.getReference();

        builder.load(reference, result);
    }

    /**
     * Reports true if the assignmnet is to a defined non-member identifier
     * or to a nested reference. These cases must be handled after context has
     * been established.
     *
     * @param toVisit
     * @return
     */
    private boolean isMutation(ASTAssignmentNode toVisit) {
        ASTReferenceNode reference = toVisit.getReference();

        // If an assignment is to a nested reference, it must be resolved
        // after other context has been established.
        if (reference.hasChild()) {
            return true;
        }

        // The other "mutation" case is that the leaf reference is to a non-
        // local identifier that is in scope here.
        return false;
    }
}
