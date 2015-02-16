/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.nodes.BlockNode;
import compiler.nodes.StatementNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.DefinitionContext;
import static compiler.interpretation.nanosyntax.NanosyntaxParser.StatementContext;

/**
 * Created by dbborens on 2/14/15.
 */
public abstract class AbstractBlockVisitor<T extends BlockNode, S extends ParserRuleContext> extends AbstractNodeVisitor {
    private Function<Stream<StatementNode>, T> constructor;

    private Class[] validPayloadContexts = new Class[] {
            StatementContext.class,
            DefinitionContext.class
    };

    public AbstractBlockVisitor(NanoToASTVisitor master, Function<Stream<StatementNode>, T> constructor) {
        super(master);
        this.constructor = constructor;
    }

    public abstract T visit(S ctx);

    protected T doVisit(S ctx, int start, int end) {
        Stream<StatementNode> children = IntStream.range(start, end)
                .mapToObj(ctx::getChild)             // int --> ParseTree
                .map(this::verifyAndAccept);

        return constructor.apply(children);
    }
    private StatementNode verifyAndAccept(ParseTree child) {
        verifyPayload(child, validPayloadContexts);
        StatementNode ret = (StatementNode) child.accept(master);
        return ret;
    }

}
