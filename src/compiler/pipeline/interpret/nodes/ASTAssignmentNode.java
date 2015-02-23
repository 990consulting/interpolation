/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

import compiler.nodes.Node;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public class ASTAssignmentNode implements ASTStatementNode {
    ASTReferenceNode reference;
    ASTValueNode value;

    public ASTAssignmentNode(ASTReferenceNode reference, ASTValueNode value) {
        this.reference = reference;
        this.value = value;
    }

    public ASTReferenceNode getReference() {
        return reference;
    }

    public ASTValueNode getValue() {
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

        if (!(childList.get(0) instanceof ASTReferenceNode)) {
            throw new IllegalStateException("Illegal node for assignment reference.");
        }

        if (!(childList.get(1) instanceof ASTValueNode)) {
            throw new IllegalStateException("Illegal node for assignment value.");
        }

        ASTReferenceNode newRefNode = (ASTReferenceNode) childList.get(0);
        ASTValueNode newValNode = (ASTValueNode) childList.get(1);

        return new ASTAssignmentNode(newRefNode, newValNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ASTAssignmentNode that = (ASTAssignmentNode) o;

        if (!reference.equals(that.reference)) return false;
        if (!value.equals(that.value)) return false;

        return true;
    }
}
