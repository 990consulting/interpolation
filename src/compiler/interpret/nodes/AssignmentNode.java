/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.nodes;

import compiler.nodes.Node;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public Stream<ASTNode> getChildren() {
        return Stream.of(reference, value);
    }

    @Override
    public ASTNode withNewChildren(Stream<? extends ASTNode> children) {
        List<Node> childList = children.collect(Collectors.<Node>toList());
        if (childList.size() != 2) {
            throw new IllegalStateException("Unexpected child count");
        }

        if (!(childList.get(0) instanceof ReferenceNode)) {
            throw new IllegalStateException("Illegal node for assignment reference.");
        }

        if (!(childList.get(1) instanceof ValueNode)) {
            throw new IllegalStateException("Illegal node for assignment value.");
        }

        ReferenceNode newRefNode = (ReferenceNode) childList.get(0);
        ValueNode newValNode = (ValueNode) childList.get(1);

        return new AssignmentNode(newRefNode, newValNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentNode that = (AssignmentNode) o;

        if (!reference.equals(that.reference)) return false;
        if (!value.equals(that.value)) return false;

        return true;
    }
}
