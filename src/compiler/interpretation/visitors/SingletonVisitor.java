/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.ValueNode;

/**
 * Created by dbborens on 2/14/15.
 */
public class SingletonVisitor extends AbstractNodeVisitor {
    public SingletonVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ValueNode visit(NanosyntaxParser.SingletonContext ctx) {
        return null;
    }
}
