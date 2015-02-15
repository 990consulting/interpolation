/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

/**
 * Created by dbborens on 2/13/15.
 */
public class ReferenceNode implements ValueNode {
    private String name;
    private ReferenceNode child;

    public ReferenceNode(String name) {
        this(name, null);
    }

    public ReferenceNode(String name, ReferenceNode child) {
        this.name = name;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public boolean hasChild() {
        return (child != null);
    }

    public ReferenceNode getChild() {
        return child;
    }
}
