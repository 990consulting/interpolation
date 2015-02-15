/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.nodes.BaseNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractVisitorTest {

    protected NanoToASTVisitor master;

    @Before
    public void init() {
        master = mock(NanoToASTVisitor.class);
    }

    protected ParseTree makeParseTree(Class<? extends BaseNode> returnClass) {
        ParseTree ret = mock(ParseTree.class);
        BaseNode node = mock(returnClass);
        when(ret.accept(master)).thenReturn(node);
        return ret;
    }

    protected ParserRuleContext makeContext(Class<? extends ParserRuleContext> contextClass,
                                            Class<? extends BaseNode> childReturnClass,
                                            int numChildren) {

        ParserRuleContext ctx = mock(contextClass);

        IntStream.range(0, numChildren)
                .forEach(i -> loadChild(i, childReturnClass, ctx));

        when(ctx.getChildCount()).thenReturn(2);

        return ctx;
    }

    protected void loadChild(int i, Class<? extends BaseNode> returnClass, ParserRuleContext ctx) {
        ParseTree child = makeParseTree(returnClass);
        when(ctx.getChild(i)).thenReturn(child);
    }

    protected List<Object> makeAcceptList(ParserRuleContext ctx) {
        return IntStream.range(0, ctx.getChildCount())
                .mapToObj(i -> ctx.getChild(i).accept(master))
                .collect(Collectors.toList());
    }
}