/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.visitors;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTBlockNode;
import compiler.interpret.nodes.ASTDefinitionNode;
import compiler.interpret.nodes.ASTStatementNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by dbborens on 2/18/15.
 */
public class ASTObjBlockVisitor {

    private ASTObjAssignmentVisitor assignmentVisitor;

    public ASTObjBlockVisitor(ASTObjAssignmentVisitor assignmentVisitor) {
        this.assignmentVisitor = assignmentVisitor;
    }

    public void visit(ASTBlockNode toVisit) {
        toVisit.getChildren()
                .map(this::cast)
                .forEach(assignmentVisitor::visit);
    }

    private ASTAssignmentNode cast(ASTStatementNode toCast) {
        if (toCast instanceof ASTDefinitionNode) {
            throw new NotImplementedException();
        } else if (!(toCast instanceof ASTAssignmentNode)) {
            throw new IllegalArgumentException("Unrecognized block-member argument to object node");
        }

        return (ASTAssignmentNode) toCast;
    }
}
