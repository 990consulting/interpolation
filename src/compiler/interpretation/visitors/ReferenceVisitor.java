/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.ReferenceNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class ReferenceVisitor extends AbstractNodeVisitor {
    public ReferenceVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ReferenceNode visit(NanosyntaxParser.ReferenceContext ctx) {
        return null;
    }
}
