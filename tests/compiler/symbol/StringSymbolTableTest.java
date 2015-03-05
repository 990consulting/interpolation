/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.pipeline.interpret.nodes.ASTPrimitiveString;
import compiler.pipeline.translate.nodes.ConstantString;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringSymbolTableTest {

    @Test
    public void testConvert() throws Exception {
        ASTPrimitiveString toConvert = new ASTPrimitiveString("test");
        ConstantString expected = new ConstantString("test");
        StringSymbolTable query = new StringSymbolTable();
        ConstantString actual = query.convert(toConvert);
        assertEquals(expected.report(), actual.report());
    }
}