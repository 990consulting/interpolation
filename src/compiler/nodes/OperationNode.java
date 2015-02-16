/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

/**
 * Created by dbborens on 2/14/15.
 */
public class OperationNode implements ValueNode {

    public ValueNode left;
    public ValueNode right;
    public PrimitiveNode<String> operator;

    public OperationNode(ValueNode left, ValueNode right, PrimitiveNode<String> operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
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
