/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ASTBlockNode;
import compiler.interpret.nodes.ASTStatementNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static compiler.interpret.nanosyntax.NanosyntaxParser.DefinitionContext;
import static compiler.interpret.nanosyntax.NanosyntaxParser.StatementContext;

/**
 * Created by dbborens on 2/14/15.
 */
public abstract class AbstractNanoBlockVisitor<T extends ASTBlockNode, S extends ParserRuleContext> extends AbstractNanoNodeVisitor {
    private Function<Stream<ASTStatementNode>, T> constructor;

    private Class[] validPayloadContexts = new Class[] {
            StatementContext.class,
            DefinitionContext.class
    };

    public AbstractNanoBlockVisitor(NanoToASTVisitor master, Function<Stream<ASTStatementNode>, T> constructor) {
        super(master);
        this.constructor = constructor;
    }

    public abstract T visit(S ctx);

    protected T doVisit(S ctx, int start, int end) {
        Stream<ASTStatementNode> children = IntStream.range(start, end)
                .mapToObj(ctx::getChild)             // int --> ParseTree
                .map(this::verifyAndAccept);

        return constructor.apply(children);
    }
    private ASTStatementNode verifyAndAccept(ParseTree child) {
        verifyPayload(child, validPayloadContexts);
        ASTStatementNode ret = (ASTStatementNode) child.accept(master);
        return ret;
    }

}
