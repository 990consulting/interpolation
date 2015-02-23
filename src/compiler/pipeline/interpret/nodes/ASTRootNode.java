/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public class ASTRootNode extends ASTBlockNode {

    public ASTRootNode(Stream<ASTValueNode> children) {
        super(children);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ASTRootNode)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public ASTNode withNewChildren(Stream<? extends ASTNode> children) {
        ASTBlockNode superRet = (ASTBlockNode) super.withNewChildren(children);
        return new ASTRootNode(superRet.getChildren());
    }
}
