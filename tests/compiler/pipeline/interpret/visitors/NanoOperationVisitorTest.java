/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import java.util.stream.Stream;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NanoOperationVisitorTest extends AbstractVisitorTest {

    private NanoOperationVisitor query;
    private ParserRuleContext left, middle, right;
    private OperationContext ctx;

    @Override
    public void init() {
        super.init();
        ctx = mock(OperationContext.class);
        when(ctx.getChildCount()).thenReturn(3);
        query = new NanoOperationVisitor(master);
        configureNormal();
    }

    @Test
    public void typicalCase() {
        ASTAssignmentNode actual = query.visit(ctx);

        ASTReferenceNode operator = (ASTReferenceNode) middle.accept(master);

        ASTValueNode leftOperandTree = (ASTValueNode) left.accept(master);
        ASTReferenceNode leftReference = new ASTReferenceNode("left");
        ASTAssignmentNode leftOperand = new ASTAssignmentNode(leftReference, leftOperandTree);

        ASTValueNode rightOperandTree = (ASTValueNode) right.accept(master);
        ASTReferenceNode rightReference = new ASTReferenceNode("right");
        ASTAssignmentNode rightOperand = new ASTAssignmentNode(rightReference, rightOperandTree);

        ASTBlockNode values = new ASTBlockNode(Stream.of(leftOperand, rightOperand));
        ASTAssignmentNode expected = new ASTAssignmentNode(operator, values);

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

        configureOutputs(ASTAssignmentNode.class,
                ASTReferenceNode.class,
                ASTAssignmentNode.class);

        assignChildren();
    }

    private void assignChildren() {
        when(ctx.getChild(0)).thenReturn(left);
        when(ctx.getChild(1)).thenReturn(middle);
        when(ctx.getChild(2)).thenReturn(right);
    }
}