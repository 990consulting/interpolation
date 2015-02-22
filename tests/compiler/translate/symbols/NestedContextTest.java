/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

/**
 * Created by dbborens on 2/21/15.
 */
public class NestedContextTest extends AbstractNestedContextTest {

    @Override
    protected AbstractNestedContext getQuery(AbstractNestedContext parent) {
        return new AbstractNestedContext(parent);
    }
}
