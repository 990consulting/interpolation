/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.nodes;

import compiler.nodes.AbstractReferenceNode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public class ASTReferenceNode extends AbstractReferenceNode implements ASTNode, ASTValueNode {
    public ASTReferenceNode(String name) {
        super(name);
    }

    public ASTReferenceNode(String name, AbstractReferenceNode child) {
        super(name, child);
    }

    @Override
    public Stream<? extends ASTNode> getChildren() {
        if (getChild() == null) {
            return Stream.empty();
        }

        return Stream.of((ASTNode) getChild());
    }

    @Override
    public ASTNode withNewChildren(Stream<? extends ASTNode> children) {
        List<ASTNode> childList = children.collect(Collectors.<ASTNode>toList());
        if (childList.size() == 0) {
            return new ASTReferenceNode(getName());
        } else if (childList.size() == 1) {
            AbstractReferenceNode newRefNode = (AbstractReferenceNode) childList.get(0);
            return new ASTReferenceNode(getName(), newRefNode);

        } else {
            throw new IllegalStateException("Unexpected child count in reference node");
        }

    }

}
