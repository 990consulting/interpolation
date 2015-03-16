/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build;

import compiler.pipeline.translate.nodes.ObjectNode;

import java.util.function.Consumer;

/**
 * Created by dbborens on 3/15/15.
 */
public interface Builder<S extends ObjectNode, T> {

    public void visit(S node, Consumer<T> callback);

}
