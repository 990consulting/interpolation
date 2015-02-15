/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.RootNode;
import compiler.nodes.StatementNode;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/14/15.
 */
public class RootVisitor extends NodeVisitor {

    public RootVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public RootNode visit(NanosyntaxParser.RootContext ctx) {
        int n = ctx.getChildCount();
        Stream<StatementNode> children = IntStream.range(0, n)
                .mapToObj(ctx::getChild)             // int --> ParseTree
                .map(child -> child.accept(master))  // ParseTree -> Object
                .map(this::verify);

        return new RootNode(children);
    }

    private StatementNode verify(Object o) {
        if (!(o instanceof StatementNode)) {
            throw new IllegalArgumentException("Unexpected node or object: " + o.getClass().toString());
        }

        return (StatementNode) o;
    }
}
