/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.context;

/**
 * A nested context in which any user assignment to identifiers
 * results in an exception. Used for reserved keywords.
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
    public void put(String identifier) {
        super.put(identifier);
    }
}
