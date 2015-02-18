/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate;

import compiler.interpret.nodes.*;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorObjectNode;
import compiler.translate.symbols.SymbolTable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by dbborens on 2/17/15.
 */
public class ASTStatementVisitor implements TranslationVisitor<ASTStatementNode> {

    private SymbolTable symbolTable;

    public ASTStatementVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public TranslatorNode visit(ASTStatementNode toVisit) {
        TranslatorObjectNode ret = new TranslatorObjectNode(symbolTable.getType());
        doVisit(ret, toVisit);
        return ret;
    }

    private void doVisit(TranslatorObjectNode ret, ASTStatementNode toVisit) {
        if (toVisit instanceof ASTBlockNode) {
            visitBlock(ret, (ASTBlockNode) toVisit);
        } else if (toVisit instanceof ASTAssignmentNode) {
            visitAssignment(ret, (ASTAssignmentNode) toVisit);
        } else if (toVisit instanceof ASTDefinitionNode) {
            throw new NotImplementedException();
        } else {
            throw new IllegalStateException("Unrecognized statement class " + toVisit.getClass().getCanonicalName());
        }
    }

    private void visitAssignment(TranslatorObjectNode node, ASTAssignmentNode toVisit) {
        ASTReferenceNode reference = toVisit.getReference();
        symbolTable.validate(reference);
        TranslatorNode translatedValue = translateValue(toVisit);

        // The name lookup should be pushed to a member index object
        node.loadMember(reference, translatedValue);
    }

    private void visitBlock(TranslatorObjectNode ret, ASTBlockNode toVisit) {
        toVisit.getChildren().forEach(child -> doVisit(ret, child));
    }

    private TranslatorNode translateValue(ASTAssignmentNode toVisit) {
        ASTValueNode astValue = toVisit.getValue();
        TranslationVisitor visitor = symbolTable.getVisitorFor(astValue);
        TranslatorNode translatedValue = visitor.visit(astValue);
        return translatedValue;
    }

}
