/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

/**
 * Created by dbborens on 2/14/15.
 */
public class PrimitiveNode<T> implements ValueNode {

    private T content;

    public PrimitiveNode(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
