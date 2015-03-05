/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTPrimitiveFloat;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.FloatPrimitiveContext;
/**
 * Created by dbborens on 2/15/15.
 */
public class NanoPrimitiveFloatVisitor extends AbstractNanoNodeVisitor {


    public NanoPrimitiveFloatVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTPrimitiveFloat visit(FloatPrimitiveContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Malformed primitive");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, CommonToken.class);

        String valueText = child.getText();
        Double value = Double.valueOf(valueText);
        return new ASTPrimitiveFloat(value);
    }
}

