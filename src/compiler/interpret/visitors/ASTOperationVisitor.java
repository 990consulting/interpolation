/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ASTOperationNode;
import compiler.interpret.nodes.ASTPrimitiveNode;
import compiler.interpret.nodes.ASTValueNode;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;

/**
 * Created by dbborens on 2/14/15.
 */
public class ASTOperationVisitor extends AbstractASTNodeVisitor {
    public ASTOperationVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTOperationNode visit(OperationContext ctx) {
        if (ctx.getChildCount() != 3) {
            throw new IllegalArgumentException("Unexpected number of children in operation");
        }

        ParseTree leftOperandTree = ctx.getChild(0);
        ParseTree operatorTree = ctx.getChild(1);
        ParseTree rightOperandTree = ctx.getChild(2);

        verifyPayload(leftOperandTree, OperandContext.class);
        verifyPayload(operatorTree, OperatorContext.class);
        verifyPayload(rightOperandTree, OperandContext.class);

        ASTValueNode leftOperand = (ASTValueNode) leftOperandTree.accept(master);
        ASTPrimitiveNode<String> operator = (ASTPrimitiveNode<String>) operatorTree.accept(master);
        ASTValueNode rightOperand = (ASTValueNode) rightOperandTree.accept(master);

        return new ASTOperationNode(leftOperand, rightOperand, operator);
    }
}
