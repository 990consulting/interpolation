/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.stream.Stream;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;

/**
 * Processes a two-operand operation in the traditional form "1 + 2"
 * to an object assignment of the form
 *
 * + {
 *     left: 1;
 *     right: 2;
 * };
 *
 * Created by dbborens on 2/14/15.
 */
public class NanoOperationVisitor extends AbstractNanoNodeVisitor {
    public NanoOperationVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTAssignmentNode visit(OperationContext ctx) {
        if (ctx.getChildCount() != 3) {
            throw new IllegalArgumentException("Unexpected number of children in operation");
        }

        ParseTree leftOperandTree = ctx.getChild(0);
        ParseTree operatorTree = ctx.getChild(1);
        ParseTree rightOperandTree = ctx.getChild(2);

        verifyPayload(leftOperandTree, OperandContext.class);
        verifyPayload(operatorTree, OperatorContext.class);
        verifyPayload(rightOperandTree, OperandContext.class);

        ASTBlockNode operands = makeOperationValue(leftOperandTree, rightOperandTree);
        ASTReferenceNode operator = (ASTReferenceNode) operatorTree.accept(master);

        ASTAssignmentNode operation = new ASTAssignmentNode(operator, operands);
        return operation;
    }

    private ASTBlockNode makeOperationValue(ParseTree leftOperandTree, ParseTree rightOperandTree) {
        ASTAssignmentNode leftOperand = makeOperand("left", leftOperandTree);
        ASTAssignmentNode rightOperand = makeOperand("right", rightOperandTree);

        Stream<ASTValueNode> childStream = Stream.of(leftOperand, rightOperand);
        return new ASTBlockNode(childStream);
    }

    private ASTAssignmentNode makeOperand(String identifier, ParseTree tree) {
        ASTReferenceNode reference = new ASTReferenceNode(identifier);
        ASTValueNode value = (ASTValueNode) tree.accept(master);
        return new ASTAssignmentNode(reference, value);
    }
}
