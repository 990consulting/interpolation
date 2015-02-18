/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.nodes;

import compiler.nodes.Node;
import compiler.translate.nodes.TranslatorNode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/14/15.
 */
public class ASTOperationNode implements ASTValueNode, TranslatorNode {

    public ASTValueNode left;
    public ASTValueNode right;
    public ASTPrimitiveNode<String> operator;

    public ASTOperationNode(ASTValueNode left, ASTValueNode right, ASTPrimitiveNode<String> operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public ASTNode withNewChildren(Stream<? extends ASTNode> children) {
        List<Node> childList = children.collect(Collectors.<Node>toList());
        if (childList.size() != 3) {
            throw new IllegalStateException("Unexpected child count");
        }

        if (!(childList.get(0) instanceof ASTValueNode)) {
            throw new IllegalStateException("Illegal node for left operand.");
        }

        if (!(childList.get(1) instanceof ASTValueNode)) {
            throw new IllegalStateException("Illegal node for right operand.");
        }

        if (!(childList.get(2) instanceof ASTPrimitiveNode)) {
            throw new IllegalStateException("Illegal node for operator.");
        }

        ASTValueNode newLeft = (ASTValueNode) childList.get(0);
        ASTValueNode newRight = (ASTValueNode) childList.get(1);
        ASTPrimitiveNode<String> newOperator = (ASTPrimitiveNode<String>) childList.get(2);
        return new ASTOperationNode(newLeft, newRight, newOperator);
    }
    public ASTValueNode getLeft() {
        return left;
    }

    public ASTValueNode getRight() {
        return right;
    }

    public ASTPrimitiveNode<String> getOperator() {
        return operator;
    }

    @Override
    public Stream<? extends ASTNode> getChildren() {
        return Stream.of(left, right, operator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ASTOperationNode that = (ASTOperationNode) o;

        if (!left.equals(that.left)) return false;
        if (!operator.equals(that.operator)) return false;
        if (!right.equals(that.right)) return false;

        return true;
    }
}
