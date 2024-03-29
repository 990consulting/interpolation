/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTStatementNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.DefinitionContext;
import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.StatementContext;

/**
 * Created by dbborens on 2/14/15.
 */
public abstract class AbstractNanoBlockVisitor<T extends ASTBlockNode, S extends ParserRuleContext> extends AbstractNanoNodeVisitor {
    private Function<Stream<ASTValueNode>, T> constructor;

    private Class[] validPayloadContexts = new Class[] {
            StatementContext.class,
            DefinitionContext.class
    };

    public AbstractNanoBlockVisitor(NanoToASTVisitor master, Function<Stream<ASTValueNode>, T> constructor) {
        super(master);
        this.constructor = constructor;
    }

    public abstract T visit(S ctx);

    protected T doVisit(S ctx, int start, int end) {
        Stream<ASTValueNode> children = IntStream.range(start, end)
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
