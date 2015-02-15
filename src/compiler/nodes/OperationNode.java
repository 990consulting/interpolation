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
    public String operator;

    public OperationNode(ValueNode left, ValueNode right, String operator) {
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

    public String getOperator() {
        return operator;
    }
}
