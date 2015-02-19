/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTValueNode;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorReferenceNode;
import compiler.translate.visitors.ASTVisitor;

/**
 * Created by dbborens on 2/18/15.
 */
public class TranslationHelper {

    TranslatorReferenceNode expectedType;
    ASTVisitor visitor;

    public TranslationHelper(TranslatorReferenceNode expectedType,
                             ASTVisitor visitor) {

        this.expectedType = expectedType;
        this.visitor = visitor;
    }

    public TranslatorNode translate(ASTAssignmentNode node) {
        ASTValueNode value = node.getValue();
        TranslatorNode ret = visitor.visit(value);
        verifyReturnType(ret);
        return ret;
    }

    private void verifyReturnType(TranslatorNode translated) {
        TranslatorReferenceNode actual = translated.getType();
        if (!expectedType.equals(actual)) {
            throw new IllegalArgumentException("Type mismatch (expected " + expectedType +", but got " + actual +").");
        }
    }

}
