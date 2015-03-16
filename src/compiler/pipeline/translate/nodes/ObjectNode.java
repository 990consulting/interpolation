/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import java.util.function.Consumer;

/**
 * Created by dbborens on 2/22/15.
 */
public interface ObjectNode<T> extends Resolvable {

    public void instantiate(Consumer<T> callback);
}
