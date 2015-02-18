/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.ASTReferenceNode;
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
        ASTReferenceNode rightSub = new ASTReferenceNode("rightSub");
        ASTReferenceNode rightSuper = new ASTReferenceNode("rightSuper", rightSub);

        ASTReferenceNode middleSub = new ASTReferenceNode("middleSub");
        ASTReferenceNode middleSuper = new ASTReferenceNode("middleSuper", middleSub);
        ASTAssignmentNode middleAssignment = new ASTAssignmentNode(middleSuper, rightSuper);

        ASTReferenceNode leftSub = new ASTReferenceNode("leftSub");
        ASTReferenceNode leftSuper = new ASTReferenceNode("leftSuper", leftSub);
        return new ASTAssignmentNode(leftSuper, middleAssignment);
    }


    private ASTNode configureOutput() {
        ASTReferenceNode rightSub = new ASTReferenceNode("rightSub");
        ASTReferenceNode rightSuper = new ASTReferenceNode("rightSuper", rightSub);
        ASTReferenceNode middleSub = new ASTReferenceNode("middleSub");
        ASTAssignmentNode middleSubAssignment = new ASTAssignmentNode(middleSub, rightSuper);

        ASTReferenceNode middleSuper = new ASTReferenceNode("middleSuper");
        ASTAssignmentNode middleSuperAssignment = new ASTAssignmentNode(middleSuper, middleSubAssignment);

        ASTReferenceNode leftSub = new ASTReferenceNode("leftSub");
        ASTAssignmentNode leftSubAssignment = new ASTAssignmentNode(leftSub, middleSuperAssignment);

        ASTReferenceNode leftSuper = new ASTReferenceNode("leftSuper");
        return new ASTAssignmentNode(leftSuper, leftSubAssignment);
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