/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.PrimitiveNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class PrimitiveVisitor extends AbstractNodeVisitor {
    public PrimitiveVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public PrimitiveNode visit(NanosyntaxParser.PrimitiveContext ctx) {
        return null;
    }

    public PrimitiveNode<String> visit(NanosyntaxParser.StringPrimitiveContext ctx) {
        return null;
    }

    public PrimitiveNode<Integer> visit(NanosyntaxParser.IntPrimitiveContext ctx) {
        return null;
    }

    public PrimitiveNode<Double> visit(NanosyntaxParser.FloatPrimitiveContext ctx) {
        return null;
    }
}
