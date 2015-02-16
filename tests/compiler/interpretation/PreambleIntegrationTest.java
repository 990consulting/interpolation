/* * Copyright (c) 2015 David Bruce Borenstein and the * Trustees of Princeton University. All rights reserved. */

package compiler.interpretation;

import compiler.interpretation.visitors.NanoToASTVisitor;
import compiler.interpretation.visitors.SlaveVisitorManager;
import compiler.nodes.*;
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
        BaseNode expected = buildExpected();
        BaseNode actual = doFixtureTraversal();
        assertEquals(expected, actual);
    }

    private BaseNode doFixtureTraversal() {
        SlaveVisitorManager manager = new SlaveVisitorManager();
        NanoToASTVisitor visitor = new NanoToASTVisitor(manager);
        AntlrBinding<BaseNode> antlr = new AntlrBinding<>(visitor);
        Interpreter interpeter = new Interpreter(antlr);
        String filename = inputFixture + "preamble.nano";
        File file = new File(filename);
        BaseNode actual = interpeter.interpret(file);
        return actual;
    }

    private BaseNode buildExpected() {
        // See attached scan (in samples) for reference. From bottom, then
        // left to right.

        ReferenceNode action = new ReferenceNode("action");
        ReferenceNode wander = new ReferenceNode("wander");
        AssignmentNode actionAssignment = new AssignmentNode(action, wander);

        ReferenceNode every = new ReferenceNode("every");
        PrimitiveNode<Double> period = new PrimitiveNode<>(1.0);
        AssignmentNode everyAssignment = new AssignmentNode(every, period);

        ReferenceNode until = new ReferenceNode("until");

        ReferenceNode time = new ReferenceNode("time");
        PrimitiveNode<String> ge = new PrimitiveNode<>(">=");
        PrimitiveNode<Double> hundred = new PrimitiveNode<>(100.0);
        OperationNode timeGeHundred = new OperationNode(time, hundred, ge);

        AssignmentNode untilAssignment = new AssignmentNode(until, timeGeHundred);

        Stream<StatementNode> behaviorStatements = Stream
                .of(
                        actionAssignment,
                        everyAssignment,
                        untilAssignment
                );

        ReferenceNode behavior = new ReferenceNode("Behavior");
        BlockNode behaviorBlock = new BlockNode(behaviorStatements);

        AssignmentNode behaviorAssigment = new AssignmentNode(behavior, behaviorBlock);

        ReferenceNode doRef = new ReferenceNode("do");
        ReferenceNode agent = new ReferenceNode("Agent", doRef);

        ReferenceNode count = new ReferenceNode("count");
        PrimitiveNode<Integer> one = new PrimitiveNode<>(1);
        AssignmentNode countAssignment = new AssignmentNode(count, one);

        AssignmentNode agentAssignment = new AssignmentNode(agent, behaviorAssigment);
        ReferenceNode description = new ReferenceNode("description");
        AssignmentNode descriptionAssignment = new AssignmentNode(description, agentAssignment);

        BlockNode scatterBlock = new BlockNode(Stream.of(
                countAssignment,
                descriptionAssignment
        ));

        ReferenceNode scatter = new ReferenceNode("scatter");
        AssignmentNode scatterAssignment = new AssignmentNode(scatter, scatterBlock);

        ReferenceNode initially = new ReferenceNode("initially");
        AssignmentNode initiallyAssignment = new AssignmentNode(initially, scatterAssignment);

        RootNode root = new RootNode(Stream.of(initiallyAssignment));
        return root;
    }
}
