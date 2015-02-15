/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.OperationNode;
import compiler.nodes.PrimitiveNode;
import compiler.nodes.ValueNode;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;

/**
 * Created by dbborens on 2/14/15.
 */
public class OperationVisitor extends AbstractNodeVisitor {
    public OperationVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public OperationNode visit(OperationContext ctx) {
        if (ctx.getChildCount() != 3) {
            throw new IllegalArgumentException("Unexpected number of children in operation");
        }

        ParseTree leftOperandTree = ctx.getChild(0);
        ParseTree operatorTree = ctx.getChild(1);
        ParseTree rightOperandTree = ctx.getChild(2);

        verifyPayload(leftOperandTree, OperandContext.class);
        verifyPayload(operatorTree, OperatorContext.class);
        verifyPayload(rightOperandTree, OperandContext.class);

        ValueNode leftOperand = (ValueNode) leftOperandTree.accept(master);
        PrimitiveNode<String> operator = (PrimitiveNode<String>) operatorTree.accept(master);
        ValueNode rightOperand = (ValueNode) rightOperandTree.accept(master);

        return new OperationNode(leftOperand, rightOperand, operator);
    }
}
