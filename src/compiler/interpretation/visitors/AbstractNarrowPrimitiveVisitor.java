/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.nodes.PrimitiveNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.function.Function;

/**
 * Created by dbborens on 2/15/15.
 */
public abstract class AbstractNarrowPrimitiveVisitor
        <T, S extends ParserRuleContext>
        extends AbstractNodeVisitor {

    private Function<String, T> cast;

    public AbstractNarrowPrimitiveVisitor(NanoToASTVisitor master,
                                          Function<String, T> cast) {

        super(master);
        this.cast = cast;
    }

    public PrimitiveNode<T> visit(S ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Malformed primitive");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, CommonToken.class);

        String valueText = child.getText();
        T value = cast.apply(valueText);
        return new PrimitiveNode<>(value);
    }
}
