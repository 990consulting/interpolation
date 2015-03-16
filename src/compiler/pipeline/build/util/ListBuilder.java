/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build.util;

import compiler.pipeline.build.Builder;
import compiler.pipeline.translate.nodes.ListObjectNode;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by dbborens on 3/13/15.
 */
public class ListBuilder<T> implements Builder<ListObjectNode, List<T>> {

    @Override
    public void visit(ListObjectNode node, Consumer<List<T>> callback) {

    }
}
