/* * Copyright (c) 2015 David Bruce Borenstein and the * Trustees of Princeton University. All rights reserved. */

package compiler.pipeline.interpret;

import compiler.pipeline.interpret.nodes.*;
import compiler.pipeline.interpret.visitors.NanoToASTVisitor;
import compiler.pipeline.interpret.visitors.SlaveNanoVisitorManager;
import org.junit.Test;
import test.FileTest;

import java.io.File;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by dbborens on 2/16/15.
 */
public class PreambleIntegrationTest implements FileTest {

    @Test
    public void testLoadPreamble() throws Exception {
        ASTNode expected = buildExpected();
        ASTNode actual = doFixtureTraversal();
        assertEquals(expected, actual);
    }

    private ASTNode doFixtureTraversal() {
        SlaveNanoVisitorManager manager = new SlaveNanoVisitorManager();
        NanoToASTVisitor visitor = new NanoToASTVisitor(manager);
        AntlrBinding<ASTNode> antlr = new AntlrBinding<>(visitor);
        Interpreter interpeter = new Interpreter(antlr);
        String filename = inputFixture + "preamble.nano";
        File file = new File(filename);
        ASTNode actual = interpeter.interpret(file);
        return actual;
    }

    private ASTNode buildExpected() {
        // See attached scan (in samples) for reference. From bottom, then
        // left to right.

        ASTReferenceNode action = new ASTReferenceNode("action");
        ASTReferenceNode wander = new ASTReferenceNode("wander");
        ASTAssignmentNode actionAssignment = new ASTAssignmentNode(action, wander);

        ASTReferenceNode every = new ASTReferenceNode("every");
        ASTPrimitiveNode<Double> period = new ASTPrimitiveNode<>(1.0);
        ASTAssignmentNode everyAssignment = new ASTAssignmentNode(every, period);

        ASTReferenceNode until = new ASTReferenceNode("until");

        ASTReferenceNode leftOperandReference = new ASTReferenceNode("left");
        ASTReferenceNode leftOperandValue = new ASTReferenceNode("time");
        ASTAssignmentNode leftOperand = new ASTAssignmentNode(leftOperandReference, leftOperandValue);

        ASTReferenceNode operator = new ASTReferenceNode(">=");

        ASTReferenceNode rightOperandReference = new ASTReferenceNode("right");
        ASTPrimitiveNode<Double> rightOperandValue = new ASTPrimitiveNode<>(100.0);
        ASTAssignmentNode rightOperand = new ASTAssignmentNode(rightOperandReference, rightOperandValue);

        Stream<ASTValueNode> operandStream = Stream.of(leftOperand, rightOperand);
        ASTBlockNode operands = new ASTBlockNode(operandStream);

        ASTAssignmentNode timeGeHundred = new ASTAssignmentNode(operator, operands);

        ASTAssignmentNode untilAssignment = new ASTAssignmentNode(until, timeGeHundred);

        Stream<ASTValueNode> behaviorStatements = Stream
                .of(
                        actionAssignment,
                        everyAssignment,
                        untilAssignment
                );

        ASTReferenceNode behavior = new ASTReferenceNode("Behavior");
        ASTBlockNode behaviorBlock = new ASTBlockNode(behaviorStatements);

        ASTAssignmentNode behaviorAssigment = new ASTAssignmentNode(behavior, behaviorBlock);

        ASTReferenceNode doRef = new ASTReferenceNode("do");
        ASTReferenceNode agent = new ASTReferenceNode("Agent", doRef);

        ASTReferenceNode count = new ASTReferenceNode("count");
        ASTPrimitiveNode<Integer> one = new ASTPrimitiveNode<>(1);
        ASTAssignmentNode countAssignment = new ASTAssignmentNode(count, one);

        ASTAssignmentNode agentAssignment = new ASTAssignmentNode(agent, behaviorAssigment);
        ASTReferenceNode description = new ASTReferenceNode("description");
        ASTAssignmentNode descriptionAssignment = new ASTAssignmentNode(description, agentAssignment);

        ASTBlockNode scatterBlock = new ASTBlockNode(Stream.of(
                countAssignment,
                descriptionAssignment
        ));

        ASTReferenceNode scatter = new ASTReferenceNode("scatter");
        ASTAssignmentNode scatterAssignment = new ASTAssignmentNode(scatter, scatterBlock);

        ASTReferenceNode initially = new ASTReferenceNode("initially");
        ASTAssignmentNode initiallyAssignment = new ASTAssignmentNode(initially, scatterAssignment);

        ASTRootNode root = new ASTRootNode(Stream.of(initiallyAssignment));
        return root;
    }
}
