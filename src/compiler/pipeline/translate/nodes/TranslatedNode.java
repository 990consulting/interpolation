/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.nodes.Node;
import compiler.nodes.TypeNode;

/**
 * Created by dbborens on 2/22/15.
 */
public interface TranslatedNode extends Node {
    public TypeNode getType();
}
