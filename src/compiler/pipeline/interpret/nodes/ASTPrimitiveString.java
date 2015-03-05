/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

/**
 * Created by dbborens on 3/5/15.
 */
public class ASTPrimitiveString extends ASTPrimitiveNode<String> {
    public ASTPrimitiveString(String content) {
        super(content);
    }
}
