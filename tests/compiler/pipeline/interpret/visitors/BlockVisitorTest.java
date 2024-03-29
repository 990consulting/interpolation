/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTDefinitionNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.interpret.nodes.ASTStatementNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BlockVisitorTest extends AbstractVisitorTest {

    private NanoBlockVisitor query;
    private BlockContext ctx;
    private ParserRuleContext leftBracket, rightBracket;
    private ParserRuleContext first, second;

    @Override
    public void init() {
        super.init();
        query = new NanoBlockVisitor(master);
        ctx = mock(BlockContext.class);

        // Actually a CommonToken, but oh well
        leftBracket = configureChild(PrimitiveContext.class, ASTPrimitiveNode.class);
        rightBracket = configureChild(PrimitiveContext.class, ASTPrimitiveNode.class);

        first = configureChild(StatementContext.class, ASTStatementNode.class);
        second = configureChild(DefinitionContext.class, ASTDefinitionNode.class);

        when(ctx.getChild(0)).thenReturn(leftBracket);
        when(ctx.getChild(1)).thenReturn(first);
        when(ctx.getChild(2)).thenReturn(second);
        when(ctx.getChild(3)).thenReturn(rightBracket);

        when(ctx.getChildCount()).thenReturn(4);
    }

    private ParserRuleContext configureChildPayload(Class<? extends ParserRuleContext> childClass) {
        ParserRuleContext ret = mock(childClass);
        when(ret.getPayload()).thenReturn(ret);
        return ret;
    }
    private ParserRuleContext configureChild(Class<? extends ParserRuleContext> childClass,
                                             Class<? extends ASTNode> childResult) {

        ParserRuleContext ret = configureChildPayload(childClass);
        ASTNode result = mock(childResult);
        when(ret.accept(master)).thenReturn(result);
        return ret;
    }

    @Test
    public void leftBracketIgnored() {
        query.visit(ctx);
        verifyNoMoreInteractions(leftBracket);
    }

    @Test
    public void rightBracketIgnored() {
        query.visit(ctx);
        verifyNoMoreInteractions(rightBracket);
    }

    @Test
    public void actualChildrenVisited() {
        List<ASTNode> expected = Arrays.asList(new ASTNode[] {
                first.accept(master),
                second.accept(master)
        });

        List<ASTNode> actual = query.visit(ctx)
                .getChildren()
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPayloadThrows() {
        first = configureChildPayload(ParserRuleContext.class);
        when(ctx.getChild(1)).thenReturn(first);
        query.visit(ctx);
    }

    // Should at least have brackets
    @Test(expected = IllegalArgumentException.class)
    public void tooFewChildrenThrows() {
        when(ctx.getChildCount()).thenReturn(1);
        query.visit(ctx);
    }
}