/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.ASTOperationNode;
import compiler.interpret.nodes.ASTPrimitiveNode;
import compiler.interpret.nodes.ASTValueNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ASTOperationVisitorTest extends AbstractVisitorTest {

    private ASTOperationVisitor query;
    private ParserRuleContext left, middle, right;
    private OperationContext ctx;

    @Override
    public void init() {
        super.init();
        ctx = mock(OperationContext.class);
        when(ctx.getChildCount()).thenReturn(3);
        query = new ASTOperationVisitor(master);
        configureNormal();
    }

    @Test
    public void visitReachesLeftOperand() {
        ASTOperationNode result = query.visit(ctx);
        ASTNode expected = left.accept(master);
        ASTNode actual = result.getLeft();
        assertEquals(expected, actual);
    }

    @Test
    public void visitReachesRightOperand() {
        ASTOperationNode result = query.visit(ctx);
        ASTNode expected = right.accept(master);
        ASTNode actual = result.getRight();
        assertEquals(expected, actual);
    }

    @Test
    public void visitReachesOperator() {
        ASTOperationNode result = query.visit(ctx);
        ASTNode expected = middle.accept(master);
        ASTNode actual = result.getOperator();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalChildCountThrows() {
        when(ctx.getChildCount()).thenReturn(4);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalLeftOperandPayloadThrows() {
        left = makePayload(ParserRuleContext.class);
        when(ctx.getChild(0)).thenReturn(left);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalRightOperandPayloadThrows() {
        right = makePayload(ParserRuleContext.class);
        when(ctx.getChild(2)).thenReturn(right);
        query.visit(ctx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalOperatorPayloadThrows() {
        middle = makePayload(ParserRuleContext.class);
        when(ctx.getChild(1)).thenReturn(middle);
        query.visit(ctx);
    }

    private void configureOutputs(Class<? extends ASTValueNode> leftOutput,
                                  Class<? extends ASTValueNode> middleOutput,
                                  Class<? extends ASTValueNode> rightOutput) {

        loadOutput(left, leftOutput);
        loadOutput(middle, middleOutput);
        loadOutput(right, rightOutput);
    }

    private void loadOutput(ParserRuleContext ctx, Class<? extends ASTValueNode> outputClass) {
        ASTValueNode output = mock(outputClass);
        when(ctx.accept(master)).thenReturn(output);
    }

    private void configurePayloads(Class<? extends ParserRuleContext> leftPayload,
                                   Class<? extends ParserRuleContext> middlePayload,
                                   Class<? extends ParserRuleContext> rightPayload) {

        left = makePayload(leftPayload);
        middle = makePayload(middlePayload);
        right = makePayload(rightPayload);
    }

    private ParserRuleContext makePayload(Class<? extends ParserRuleContext> payloadClass) {
        ParserRuleContext payload = mock(payloadClass);
        ParserRuleContext child = mock(ParserRuleContext.class);
        when(child.getPayload()).thenReturn(payload);
        return child;
    }

    private void configureNormal() {
        configurePayloads(OperandContext.class,
                OperatorContext.class,
                OperandContext.class);

        configureOutputs(ASTValueNode.class,
                ASTPrimitiveNode.class,
                ASTValueNode.class);

        assignChildren();
    }

    private void assignChildren() {
        when(ctx.getChild(0)).thenReturn(left);
        when(ctx.getChild(1)).thenReturn(middle);
        when(ctx.getChild(2)).thenReturn(right);
    }
}