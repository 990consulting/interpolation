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
public class StatementVisitor implements TranslationVisitor<StatementNode> {

    private SymbolTable symbolTable;

    @Override
    public TranslatorNode visit(StatementNode toVisit) {
        TranslatorObjectNode ret = new TranslatorObjectNode(symbolTable.getType());
        doVisit(ret, toVisit);
        return ret;
    }

    private void doVisit(TranslatorObjectNode ret, StatementNode toVisit) {
        if (toVisit instanceof BlockNode) {
            visitBlock(ret, (BlockNode) toVisit);
        } else if (toVisit instanceof AssignmentNode) {
            visitAssignment(ret, (AssignmentNode) toVisit);
        } else if (toVisit instanceof DefinitionNode) {
            throw new NotImplementedException();
        } else {
            throw new IllegalStateException("Unrecognized statement class " + toVisit.getClass().getCanonicalName());
        }
    }

    private void visitAssignment(TranslatorObjectNode node, AssignmentNode toVisit) {
        ReferenceNode reference = toVisit.getReference();
        symbolTable.validate(reference);
        TranslatorNode translatedValue = translateValue(toVisit);

        // The name lookup should be pushed to a member index object
        node.loadMember(reference, translatedValue);
    }

    private void visitBlock(TranslatorObjectNode ret, BlockNode toVisit) {
        toVisit.getChildren().forEach(child -> doVisit(ret, child));
    }

    private TranslatorNode translateValue(AssignmentNode toVisit) {
        ValueNode astValue = toVisit.getValue();
        TranslationVisitor visitor = symbolTable.getVisitorFor(astValue);
        TranslatorNode translatedValue = visitor.visit(astValue);
        return translatedValue;
    }

}
