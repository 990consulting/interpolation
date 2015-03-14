/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build.builders;

import compiler.pipeline.translate.nodes.ObjectNode;

/**
 * Created by dbborens on 3/13/15.
 */
public interface Builder<T> {

    public T instantiate(ObjectNode node);
}
