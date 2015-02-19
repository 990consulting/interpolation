/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate;

import compiler.nodes.AbstractReferenceNode;

/**
 * Created by dbborens on 2/17/15.
 */
public class ReferenceNameConverter {

    public String convert(AbstractReferenceNode node) {
        if (node.hasChild()) {
            throw new IllegalStateException("Name conversion of non-leaf reference");
        }
        return node.getName();
    }
}
