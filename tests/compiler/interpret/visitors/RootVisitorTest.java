/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.DefinitionNode;
import compiler.interpret.nodes.StatementNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RootVisitorTest extends AbstractVisitorTest {

    private RootVisitor query;
    private RootContext ctx;
    private ParserRuleContext first, second;

    @Override
    public void init() {
        super.init();
        query = new RootVisitor(master);
        ctx = mock(RootContext.class);

        first = configureChild(StatementContext.class, StatementNode.class);
        second = configureChild(DefinitionContext.class, DefinitionNode.class);

        when(ctx.getChild(0)).thenReturn(first);
        when(ctx.getChild(1)).thenReturn(second);

        when(ctx.getChildCount()).thenReturn(2);
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
    public void allChildrenVisited() {
        List<ASTNode> expected = Arrays.asList(new ASTNode[]{
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
}