/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.PrimitiveNode;
import compiler.nodes.ValueNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class OperatorVisitor extends NodeVisitor {
    public OperatorVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public PrimitiveNode<String> visit(NanosyntaxParser.OperatorContext ctx) {
        return null;
    }
}
