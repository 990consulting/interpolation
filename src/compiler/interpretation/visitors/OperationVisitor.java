/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.OperationNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class OperationVisitor extends NodeVisitor {
    public OperationVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public OperationNode visit(NanosyntaxParser.OperationContext ctx) {
        return null;
    }
}
