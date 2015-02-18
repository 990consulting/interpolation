/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

/**
 * A ListSymbolTable corresponds to an ordered set of zero or more elements
 * that are members of a specified type. The rules are as follows:
 *
 * (1) Each input must be a definition, an object or an operation.
 * (2) If the input is an object, it must have the list's specified type.
 * (3) If the input is an operation, it must have a return type corresponding
 *     to the list's specified type.
 *
 * Created by dbborens on 2/18/15.
 */
public class ListSymbolTable {
}
