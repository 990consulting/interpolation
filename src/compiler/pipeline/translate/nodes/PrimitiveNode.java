/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

/**
 * Created by dbborens on 2/22/15.
 */
public abstract class PrimitiveNode<T> implements ObjectNode {

    private T value;

    public PrimitiveNode(T value) {
        this.value = value;
    }

    public T report() {
        return value;
    }
}
