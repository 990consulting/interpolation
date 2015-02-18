/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.nodes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public class ASTBlockNode implements ASTValueNode {

    private List<ASTStatementNode> childList;

    public ASTBlockNode(Stream<ASTStatementNode> children) {
        childList = children
                .collect(Collectors.<ASTStatementNode>toList());
    }

    public Stream<ASTStatementNode> getChildren() {
        return childList.stream();
    }

    @Override
    public ASTNode withNewChildren(Stream<? extends ASTNode> children) {
        try {
            Stream<ASTStatementNode> asStatements =  children
                    .map(child -> (ASTStatementNode) child);
            return new ASTBlockNode(asStatements);

        } catch (ClassCastException ex) {
            throw new IllegalStateException("Illegal node for statement block");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ASTBlockNode blockNode = (ASTBlockNode) o;

        if (!childList.equals(blockNode.childList)) return false;

        return true;
    }
}
