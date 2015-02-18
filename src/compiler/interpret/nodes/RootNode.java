/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.nodes;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public class RootNode extends BlockNode {

    public RootNode(Stream<StatementNode> children) {
        super(children);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof RootNode)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public ASTNode withNewChildren(Stream<? extends ASTNode> children) {
        BlockNode superRet = (BlockNode) super.withNewChildren(children);
        return new RootNode(superRet.getChildren());
    }
}
