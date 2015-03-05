/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTPrimitiveFloat;
import compiler.pipeline.interpret.nodes.ASTPrimitiveInteger;
import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.interpret.nodes.ASTPrimitiveString;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
/**
 * Created by dbborens on 2/14/15.
 */
public class NanoPrimitiveVisitor extends AbstractNanoNodeVisitor {
    public NanoPrimitiveVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTPrimitiveNode visit(PrimitiveContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Unexpected child count on primitive");
        }

        ParseTree child = ctx.getChild(0);

        return narrow(child);
    }

    private ASTPrimitiveNode narrow(ParseTree child) {
        Object payload = child.getPayload();

        if (StringPrimitiveContext.class.isInstance(payload)) {
            return (ASTPrimitiveString) child.accept(master);
        } else if (IntPrimitiveContext.class.isInstance(payload)) {
            return (ASTPrimitiveInteger) child.accept(master);
        } else if (FloatPrimitiveContext.class.isInstance(payload)) {
            return (ASTPrimitiveFloat) child.accept(master);
        } else {
            throw new IllegalArgumentException("Unrecognized primitive");
        }
    }
}
