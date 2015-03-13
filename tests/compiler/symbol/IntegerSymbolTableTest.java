/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.pipeline.interpret.nodes.ASTPrimitiveInteger;
import compiler.pipeline.translate.nodes.ConstantInteger;
import compiler.symbol.tables.primitive.IntegerSymbolTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegerSymbolTableTest {

    @Test
    public void testConvert() throws Exception {
        ASTPrimitiveInteger toConvert = new ASTPrimitiveInteger(1);
        ConstantInteger expected = new ConstantInteger(1);
        IntegerSymbolTable query = new IntegerSymbolTable();
        ConstantInteger actual = query.convert(toConvert);
        assertEquals(expected.report(), actual.report());
    }
}