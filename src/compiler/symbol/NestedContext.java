/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.util.IllegalAssignmentError;

/**
 * Created by dbborens on 2/21/15.
 */
public class NestedContext extends AbstractNestedContext {
    @Override
    public void put(String identifier, Symbol symbol) {
        try {
            super.put(identifier, symbol);
        } catch (IllegalAssignmentError ex) {
            throw new IllegalStateException("Something went wrong: NestedContex through an illegal assignment " +
                    "exception, but I think that all assignments for this class are legal.");
        }
    }
}
