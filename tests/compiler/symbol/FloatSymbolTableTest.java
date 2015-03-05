/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.pipeline.interpret.nodes.ASTPrimitiveFloat;
import compiler.pipeline.translate.nodes.ConstantFloat;
import org.junit.Test;
import test.TestBase;

import static org.junit.Assert.assertEquals;

public class FloatSymbolTableTest extends TestBase {

    @Test
    public void testConvert() throws Exception {
        ASTPrimitiveFloat toConvert = new ASTPrimitiveFloat(1.0);
        ConstantFloat expected = new ConstantFloat(1.0);
        FloatSymbolTable query = new FloatSymbolTable();
        ConstantFloat actual = query.convert(toConvert);
        assertEquals(expected.report(), actual.report(), epsilon());
    }

}