/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import test.TestBase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractVisitorTest extends TestBase {

    protected NanoToASTVisitor master;

    @Before
    public void init() {
        master = mock(NanoToASTVisitor.class);
    }

    protected ParseTree makeParseTree(Class<? extends ASTNode> returnClass) {
        ParseTree ret = mock(ParseTree.class);
        ASTNode node = mock(returnClass);
        when(ret.accept(master)).thenReturn(node);
        return ret;
    }

    protected ParserRuleContext makeContext(Class<? extends ParserRuleContext> contextClass,
                                            Class<? extends ASTNode> childReturnClass,
                                            int numChildren) {

        ParserRuleContext ctx = mock(contextClass);

        IntStream.range(0, numChildren)
                .forEach(i -> loadChild(i, childReturnClass, ctx));

        when(ctx.getChildCount()).thenReturn(2);

        return ctx;
    }

    protected void loadChild(int i, Class<? extends ASTNode> returnClass, ParserRuleContext ctx) {
        ParseTree child = makeParseTree(returnClass);
        when(ctx.getChild(i)).thenReturn(child);
    }

    protected List<Object> makeAcceptList(ParserRuleContext ctx) {
        return IntStream.range(0, ctx.getChildCount())
                .mapToObj(i -> ctx.getChild(i).accept(master))
                .collect(Collectors.toList());
    }
}