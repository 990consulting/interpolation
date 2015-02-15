/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by dbborens on 2/14/15.
 */
public abstract class AbstractNodeVisitor {

    protected NanoToASTVisitor master;

    public AbstractNodeVisitor(NanoToASTVisitor master) {
        this.master = master;
    }

    protected void verifyPayload(ParseTree ctx, Class expected) {
        if (ctx.getPayload() == null) {
            throw new IllegalArgumentException("Empty payload");
        }

        Object payload = ctx.getPayload();

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
