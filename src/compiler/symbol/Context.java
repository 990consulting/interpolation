/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.util.IllegalAssignmentError;
import compiler.util.UnrecognizedIdentifierError;

/**
 * Created by dbborens on 2/21/15.
 */
public interface Context {
    void put(String identifier, Symbol symbol) throws IllegalAssignmentError;

    boolean has(String identifier);

    Symbol get(String identifier) throws UnrecognizedIdentifierError;
}
