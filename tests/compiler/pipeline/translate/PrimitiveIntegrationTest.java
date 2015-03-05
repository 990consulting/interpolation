/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate;

import compiler.pipeline.interpret.nodes.ASTPrimitiveFloat;
import compiler.pipeline.interpret.nodes.ASTPrimitiveInteger;
import compiler.pipeline.interpret.nodes.ASTPrimitiveString;
import compiler.pipeline.translate.nodes.ConstantFloat;
import compiler.pipeline.translate.nodes.ConstantInteger;
import compiler.pipeline.translate.nodes.ConstantString;
import compiler.symbol.FloatSymbolTable;
import compiler.symbol.IntegerSymbolTable;
import compiler.symbol.StringSymbolTable;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dbborens on 3/5/15.
 */
public class PrimitiveIntegrationTest {

    private TranslationVisitor query;

    @Before
    public void init() throws Exception {
        query = new TranslationVisitor();
    }

    @Test
    public void integerCase() throws Exception {
        IntegerSymbolTable st = new IntegerSymbolTable();
        ASTPrimitiveInteger input = new ASTPrimitiveInteger(1);
        ConstantInteger output = (ConstantInteger) query.translate(input, st, null);
        assertTrue(1 == output.report());
    }

    @Test
    public void stringCase() throws Exception {
        StringSymbolTable st = new StringSymbolTable();
        ASTPrimitiveString input = new ASTPrimitiveString("test");
        ConstantString output = (ConstantString) query.translate(input, st, null);
        assertEquals("test", output.report());
    }

    @Test
    public void floatCase() throws Exception {
        FloatSymbolTable st = new FloatSymbolTable();
        ASTPrimitiveFloat input = new ASTPrimitiveFloat(1.0);
        ConstantFloat output = (ConstantFloat) query.translate(input, st, null);
        assertTrue(1 == output.report());
    }
}
