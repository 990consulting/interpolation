/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

/**
 * Created by dbborens on 2/13/15.
 */
public abstract class AbstractReferenceNode implements Node {
    private String identifier;
    private AbstractReferenceNode child;

    public AbstractReferenceNode(String identifier) {
        this(identifier, null);
    }

    public AbstractReferenceNode(String identifier, AbstractReferenceNode child) {
        this.identifier = identifier;
        this.child = child;
    }

    public String getIdentifier() {
        return identifier;
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
        if (!identifier.equals(that.identifier)) return false;

        return true;
    }
}
