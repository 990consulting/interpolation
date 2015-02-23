/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeNodeTest {

    @Test
    public void stringOrphan() throws Exception {
        TypeNode query = new TypeNode("orphan");
        assertEquals("orphan", query.toString());
    }

    public void stringNested() throws Exception {
        TypeNode grandparent = new TypeNode("grandparent");
        TypeNode parent = new TypeNode("parent", grandparent);
        TypeNode child = new TypeNode("child", parent);

        assertEquals("grandparent->parent->child", child.toString());
    }
}