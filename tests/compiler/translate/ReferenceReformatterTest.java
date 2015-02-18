/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate;

import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.AssignmentNode;
import compiler.interpret.nodes.ReferenceNode;
import compiler.nodes.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReferenceReformatterTest {

    private ReferenceReformatter query;

    @Before
    public void init() throws Exception {
        query = new ReferenceReformatter();
    }

    private ASTNode configureInput() {
        ReferenceNode rightSub = new ReferenceNode("rightSub");
        ReferenceNode rightSuper = new ReferenceNode("rightSuper", rightSub);

        ReferenceNode middleSub = new ReferenceNode("middleSub");
        ReferenceNode middleSuper = new ReferenceNode("middleSuper", middleSub);
        AssignmentNode middleAssignment = new AssignmentNode(middleSuper, rightSuper);

        ReferenceNode leftSub = new ReferenceNode("leftSub");
        ReferenceNode leftSuper = new ReferenceNode("leftSuper", leftSub);
        return new AssignmentNode(leftSuper, middleAssignment);
    }


    private ASTNode configureOutput() {
        ReferenceNode rightSub = new ReferenceNode("rightSub");
        ReferenceNode rightSuper = new ReferenceNode("rightSuper", rightSub);
        ReferenceNode middleSub = new ReferenceNode("middleSub");
        AssignmentNode middleSubAssignment = new AssignmentNode(middleSub, rightSuper);

        ReferenceNode middleSuper = new ReferenceNode("middleSuper");
        AssignmentNode middleSuperAssignment = new AssignmentNode(middleSuper, middleSubAssignment);

        ReferenceNode leftSub = new ReferenceNode("leftSub");
        AssignmentNode leftSubAssignment = new AssignmentNode(leftSub, middleSuperAssignment);

        ReferenceNode leftSuper = new ReferenceNode("leftSuper");
        return new AssignmentNode(leftSuper, leftSubAssignment);
    }


    /**
     * Verify that every non-leaf reference on the left side of an assignment
     * is split (recursively) into a leaf reference and a nested assignment,
     * but that non-leaf assignments on the right are unaffected.
     */
    @Test
    public void testReformatted() {
        ASTNode input = configureInput();
        ASTNode expected = configureOutput();
        Node actual = query.reformat(input);

        assertEquals(expected, actual);
    }
}