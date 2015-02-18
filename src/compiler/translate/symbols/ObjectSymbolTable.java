/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

/**
 * An object symbol table is a symbol table with a defined set of
 * named members. The object corresponds to a member of a predetermined type.
 * The following rules apply:
 *
 * (1) The content must consist of zero or more statements.
 * (2) All content statements must be assignments or definitions.
 * (3) The referent of any assignment must be a named member.
 * (4) The value of any assignment must be an object or an operation.
 * (5) If the value of the assignment is an object, it must have the
 *     object type expected by the referent.
 * (6) If the value of the assignment is an operation, it must have
 *     return type corresponding to the type expected by the referent.
 *
 * The return type of this ObjectSymbolTable is a TranslatorObjectNode.
 *
 * Created by dbborens on 2/18/15.
 */
public class ObjectSymbolTable {
}
