/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ConstantStringTest {

    @Test
    public void getInstanceClass() throws Exception {
        ConstantString query = new ConstantString("");
        assertSame(String.class, query.getInstanceClass());
    }
}