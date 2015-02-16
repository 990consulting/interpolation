/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public class BlockNode implements ValueNode {

    private List<StatementNode> childList;

    public BlockNode(Stream<StatementNode> children) {
        childList = children
                .collect(Collectors.<StatementNode>toList());
    }

    public Stream<StatementNode> getChildren() {
        return childList.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockNode blockNode = (BlockNode) o;

        if (!childList.equals(blockNode.childList)) return false;

        return true;
    }
}
