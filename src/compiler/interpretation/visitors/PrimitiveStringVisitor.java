/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.PrimitiveNode;

import java.util.function.Function;

/**
 * Created by dbborens on 2/15/15.
 */
public class PrimitiveStringVisitor extends AbstractNodeVisitor {

    public PrimitiveStringVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public PrimitiveNode<String> visit(NanosyntaxParser.StringPrimitiveContext ctx) {
        return null;
    }
}