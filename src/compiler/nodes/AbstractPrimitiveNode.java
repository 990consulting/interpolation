/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

/**
 * Created by dbborens on 2/17/15.
 */
public class AbstractPrimitiveNode<T> implements Node{
    private T content;

    public AbstractPrimitiveNode(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractPrimitiveNode that = (AbstractPrimitiveNode) o;

        if (!content.equals(that.content)) return false;

        return true;
    }
}
