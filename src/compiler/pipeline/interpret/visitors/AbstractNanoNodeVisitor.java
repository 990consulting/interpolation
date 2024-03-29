/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by dbborens on 2/14/15.
 */
public abstract class AbstractNanoNodeVisitor {

    protected NanoToASTVisitor master;

    public AbstractNanoNodeVisitor(NanoToASTVisitor master) {
        this.master = master;
    }

    protected void verifyPayload(ParseTree child, Class expected) {
        if (child.getPayload() == null) {
            throw new IllegalArgumentException("Empty payload");
        }

        Object payload = child.getPayload();

        if (!expected.isInstance(payload)) {
            throw new IllegalArgumentException("Unexpected payload class");
        }
    }

    protected void verifyPayload(ParseTree child, Class[] legalChildClasses) {
        if (child.getPayload() == null) {
            throw new IllegalArgumentException("Empty payload");
        }

        Object payload = child.getPayload();

        for(Class clazz : legalChildClasses) {
            if (clazz.isInstance(payload)) {
                return;
            }
        }

        throw new IllegalArgumentException("Unexpected payload class");
    }
}
