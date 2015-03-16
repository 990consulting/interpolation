/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ConstantFloatTest {

    @Test
    public void getInstanceClass() throws Exception {
        ConstantFloat query = new ConstantFloat(1.0);
        assertSame(Double.class, query.getInstanceClass());
    }
}