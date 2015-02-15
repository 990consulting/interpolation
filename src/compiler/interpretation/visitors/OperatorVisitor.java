/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.PrimitiveNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by dbborens on 2/14/15.
 */
public class OperatorVisitor extends AbstractNodeVisitor {
    public OperatorVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public PrimitiveNode<String> visit(NanosyntaxParser.OperatorContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Malformed operator");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, CommonToken.class);
        return new PrimitiveNode<> (child.getText());
    }
}
