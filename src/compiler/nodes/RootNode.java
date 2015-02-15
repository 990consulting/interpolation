/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public class RootNode extends BlockNode {

    public RootNode(Stream<StatementNode> children) {
        super(children);
    }

}
