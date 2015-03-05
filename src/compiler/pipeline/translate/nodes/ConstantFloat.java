/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

/**
 * Created by dbborens on 3/5/15.
 */
public class ConstantFloat extends PrimitiveNode<Double> {
    public ConstantFloat(Double value) {
        super(value);
    }
}
