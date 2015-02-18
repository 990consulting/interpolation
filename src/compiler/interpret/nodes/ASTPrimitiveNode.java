/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.nodes;

import compiler.nodes.AbstractPrimitiveNode;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/14/15.
 */
public class ASTPrimitiveNode<T> extends AbstractPrimitiveNode<T> implements ASTValueNode {

    public ASTPrimitiveNode(T content) {
        super(content);
    }

    @Override
    public Stream<? extends ASTNode> getChildren() {
        return Stream.empty();
    }

    public ASTPrimitiveNode withNewChildren(Stream<? extends ASTNode> children) {
        if (children.count() != 0) {
            throw new IllegalStateException("Unexpected children in primitive node");
        }
        return this;
    }
}
