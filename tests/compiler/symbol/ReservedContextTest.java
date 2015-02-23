/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.util.IllegalAssignmentError;
import org.junit.Test;

import static org.mockito.Mockito.when;

/**
 * Created by dbborens on 2/21/15.
 */
public class ReservedContextTest extends AbstractNestedContextTest {

    @Override
    protected AbstractNestedContext getQuery(AbstractNestedContext parent) {
        return new ReservedContext(parent);
    }


    @Test(expected = IllegalAssignmentError.class)
    public void putAfterHasLocalThrows() throws Exception {
        query.put(identifier, symbol);
        query.put(identifier, symbol);
    }

    @Test(expected = IllegalAssignmentError.class)
    public void putAfterParentHasThrows() throws Exception {
        when(parent.has(identifier)).thenReturn(true);
        query.put(identifier, symbol);
    }
}
