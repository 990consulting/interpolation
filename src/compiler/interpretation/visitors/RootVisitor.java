/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.RootNode;
import compiler.nodes.StatementNode;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/14/15.
 */
public class RootVisitor extends AbstractBlockVisitor<RootNode, NanosyntaxParser.RootContext> {

    public RootVisitor(NanoToASTVisitor master) {
        super(master, RootNode::new);
    }
}
