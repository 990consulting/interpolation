/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ConstantIntegerTest {

    @Test
    public void getInstanceClass() throws Exception {
        ConstantInteger query = new ConstantInteger(1);
        assertSame(Integer.class, query.getInstanceClass());
    }
}