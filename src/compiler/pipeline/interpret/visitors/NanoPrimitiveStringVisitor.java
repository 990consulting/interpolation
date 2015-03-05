/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTPrimitiveString;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.StringPrimitiveContext;
/**
 * Created by dbborens on 2/15/15.
 */
public class NanoPrimitiveStringVisitor extends AbstractNanoNodeVisitor {


    public NanoPrimitiveStringVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTPrimitiveString visit(StringPrimitiveContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Malformed primitive");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, CommonToken.class);

        String valueText = child.getText();
        valueText = valueText.replaceAll("^\"|\"$", "");
        return new ASTPrimitiveString(valueText);
    }
}
