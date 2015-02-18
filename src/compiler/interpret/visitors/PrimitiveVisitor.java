/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.PrimitiveNode;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;
/**
 * Created by dbborens on 2/14/15.
 */
public class PrimitiveVisitor extends AbstractNodeVisitor {
    public PrimitiveVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public PrimitiveNode visit(PrimitiveContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Unexpected child count on primitive");
        }

        ParseTree child = ctx.getChild(0);

        return narrow(child);
    }

    private PrimitiveNode narrow(ParseTree child) {
        Object payload = child.getPayload();

        if (StringPrimitiveContext.class.isInstance(payload)) {
            return (PrimitiveNode<String>) child.accept(master);
        } else if (IntPrimitiveContext.class.isInstance(payload)) {
            return (PrimitiveNode<Integer>) child.accept(master);
        } else if (FloatPrimitiveContext.class.isInstance(payload)) {
            return (PrimitiveNode<Double>) child.accept(master);
        } else {
            throw new IllegalArgumentException("Unrecognized primitive");
        }
    }
}
