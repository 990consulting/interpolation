/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

/**
 * Created by dbborens on 3/5/15.
 */
public class ASTPrimitiveInteger extends ASTPrimitiveNode<Integer> {
    public ASTPrimitiveInteger(Integer content) {
        super(content);
    }
}
