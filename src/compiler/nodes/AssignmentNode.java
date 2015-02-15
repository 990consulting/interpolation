/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

/**
 * Created by dbborens on 2/13/15.
 */
public class AssignmentNode implements StatementNode {
    ReferenceNode reference;
    ValueNode value;

    public AssignmentNode(ReferenceNode reference, ValueNode value) {
        this.reference = reference;
        this.value = value;
    }

    public ReferenceNode getReference() {
        return reference;
    }

    public ValueNode getValue() {
        return value;
    }
}
