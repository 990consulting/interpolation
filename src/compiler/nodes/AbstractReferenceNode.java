/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

/**
 * Created by dbborens on 2/13/15.
 */
public abstract class AbstractReferenceNode implements Node {
    private String name;
    private AbstractReferenceNode child;

    public AbstractReferenceNode(String name) {
        this(name, null);
    }

    public AbstractReferenceNode(String name, AbstractReferenceNode child) {
        this.name = name;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public boolean hasChild() {
        return (child != null);
    }


    public AbstractReferenceNode getChild() {
        return child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractReferenceNode that = (AbstractReferenceNode) o;

        if (child != null ? !child.equals(that.child) : that.child != null) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }
}
