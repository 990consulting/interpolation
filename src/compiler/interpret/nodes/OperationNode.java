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
public class OperationNode implements ValueNode, TranslatorNode {

    public ValueNode left;
    public ValueNode right;
    public PrimitiveNode<String> operator;

    public OperationNode(ValueNode left, ValueNode right, PrimitiveNode<String> operator) {
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

        if (!(childList.get(0) instanceof ValueNode)) {
            throw new IllegalStateException("Illegal node for left operand.");
        }

        if (!(childList.get(1) instanceof ValueNode)) {
            throw new IllegalStateException("Illegal node for right operand.");
        }

        if (!(childList.get(2) instanceof PrimitiveNode)) {
            throw new IllegalStateException("Illegal node for operator.");
        }

        ValueNode newLeft = (ValueNode) childList.get(0);
        ValueNode newRight = (ValueNode) childList.get(1);
        PrimitiveNode<String> newOperator = (PrimitiveNode<String>) childList.get(2);
        return new OperationNode(newLeft, newRight, newOperator);
    }
    public ValueNode getLeft() {
        return left;
    }

    public ValueNode getRight() {
        return right;
    }

    public PrimitiveNode<String> getOperator() {
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

        OperationNode that = (OperationNode) o;

        if (!left.equals(that.left)) return false;
        if (!operator.equals(that.operator)) return false;
        if (!right.equals(that.right)) return false;

        return true;
    }
}
