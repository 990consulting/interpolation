/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.util.IllegalAssignmentException;

/**
 * A nested context in which any occlusion of identifiers results in an
 * exception. Used for reserved keywords.
 *
 * Created by dbborens on 2/21/15.
 */
public class ReservedContext extends AbstractNestedContext {
    public ReservedContext() {
        super();
    }

    public ReservedContext(AbstractNestedContext parent) {
        super(parent);
    }

    @Override
    public void put(String identifier, Symbol symbol) throws IllegalAssignmentException {
        if (has(identifier)) {
            throw new IllegalAssignmentException();
        }
        super.put(identifier, symbol);
    }
}
