/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.util.IllegalAssignmentException;
import compiler.util.UnrecognizedIdentifierException;

/**
 * Created by dbborens on 2/21/15.
 */
public interface Context {
    void put(String identifier, Symbol symbol) throws IllegalAssignmentException;

    boolean has(String identifier);

    Symbol get(String identifier) throws UnrecognizedIdentifierException;
}
