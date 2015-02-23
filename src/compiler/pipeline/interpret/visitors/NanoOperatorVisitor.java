/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by dbborens on 2/14/15.
 */
public class NanoOperatorVisitor extends AbstractNanoNodeVisitor {
    public NanoOperatorVisitor(NanoToASTVisitor master) {
        super(master);
    }

    public ASTReferenceNode visit(NanosyntaxParser.OperatorContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalArgumentException("Malformed operator");
        }

        ParseTree child = ctx.getChild(0);
        verifyPayload(child, CommonToken.class);
        String identifier = child.getText();
        ASTReferenceNode referenceNode = new ASTReferenceNode(identifier);
        return referenceNode;
    }
}
