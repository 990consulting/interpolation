/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.primitive;

import compiler.pipeline.interpret.nodes.ASTPrimitiveFloat;
import compiler.pipeline.translate.nodes.ConstantFloat;

/**
 * Created by dbborens on 3/5/15.
 */
public class FloatSymbolTable implements PrimitiveSymbolTable<ConstantFloat, ASTPrimitiveFloat> {
    @Override
    public ConstantFloat convert(ASTPrimitiveFloat toInterpret) {
        Double value = toInterpret.getContent();
        return new ConstantFloat(value);
    }
}
