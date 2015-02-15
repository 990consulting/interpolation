/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.BlockNode;
import compiler.nodes.RootNode;
import compiler.nodes.StatementNode;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/14/15.
 */
public class AbstractBlockVisitor<T extends BlockNode, S extends ParserRuleContext> extends NodeVisitor {
    private Function<Stream<StatementNode>, T> constructor;

    public AbstractBlockVisitor(NanoToASTVisitor master, Function<Stream<StatementNode>, T> constructor) {
        super(master);
        this.constructor = constructor;
    }

    public T visit(S ctx) {
        int n = ctx.getChildCount();
        Stream<StatementNode> children = IntStream.range(0, n)
                .mapToObj(ctx::getChild)             // int --> ParseTree
                .map(child -> child.accept(master))  // ParseTree -> Object
                .map(this::verify);

        return constructor.apply(children);
    }

    private StatementNode verify(Object o) {
        if (!(o instanceof StatementNode)) {
            throw new IllegalArgumentException("Unexpected node or object: " + o.getClass().toString());
        }

        return (StatementNode) o;
    }
}
