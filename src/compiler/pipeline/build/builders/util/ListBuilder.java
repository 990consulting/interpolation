/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build.builders.util;

import compiler.pipeline.build.builders.Builder;
import compiler.pipeline.translate.nodes.ObjectNode;

import java.util.List;

/**
 * Created by dbborens on 3/13/15.
 */
public class ListBuilder<T> implements Builder<List<T>> {

    @Override
    public List instantiate(ObjectNode node) {
        return null;
    }
}
