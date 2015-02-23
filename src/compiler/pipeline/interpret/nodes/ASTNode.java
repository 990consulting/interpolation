/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

import compiler.nodes.Node;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public interface ASTNode extends Node {

    /**
     * Returns all children of this node. See implementation for order.
     * @return
     */
    public Stream<? extends ASTNode> getChildren();

    /**
     * Returns a copy of this node, preserving all non-child decorations,
     * with the children specified in the stream. The children must be
     * provided in the order expected for getChildren.
     *
     * @param children
     * @return
     */
    public ASTNode withNewChildren(Stream<? extends ASTNode> children);
}
