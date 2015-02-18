/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate;

import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.AssignmentNode;
import compiler.interpret.nodes.ReferenceNode;
import compiler.interpret.nodes.ValueNode;
import compiler.nodes.Node;

import java.util.stream.Stream;

/**
 * Traverses the AST, converting nested left references
 * to assignments. See tests for examples.
 *
 * Created by dbborens on 2/17/15.
 */
public class ReferenceReformatter {

    public ASTNode reformat(ASTNode root) {
        // Base case: we're at a leaf; return that leaf.
        if (root.getChildren().count() == 0) {
            return root;
        }

        // Special case: We're at an assignment with a left nested reference;
        //               reformat.
        else if (needsReformatting(root)) {
            AssignmentNode aRoot = (AssignmentNode) root;

            // Get the child of the reference (which may itself be nested).
            ReferenceNode subLeft = (ReferenceNode) aRoot.getReference().getChild();

            // Create a new reference node with the name of the top-level
            // reference, and without any children.
            String superName = aRoot.getReference().getName();
            ReferenceNode superLeft = new ReferenceNode(superName);

            ValueNode value = aRoot.getValue();

            // Create an assignment of childReference: value.
            AssignmentNode newRight = new AssignmentNode(subLeft, value);

            // Create a top-level assignment of the new value node (newRight)
            // to the top-level leaf reference (superLeft). Assign this to
            // root so it gets visited in the general case.
            root = new AssignmentNode(superLeft, newRight);
        }

        Stream<? extends ASTNode> rfChildren = root.getChildren()
                .map(child -> reformat(child));

        return root.withNewChildren(rfChildren);
    }

    public <T extends Node> boolean needsReformatting(T root) {
        if (!(root instanceof AssignmentNode)) {
            return false;
        }

        AssignmentNode aRoot = (AssignmentNode) root;

        return aRoot.getReference().hasChild();
    }
}
