/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;

/**
 * Created by dbborens on 2/15/15.
 */
public class NanoPrimitiveStringVisitor
        extends AbstractNanoNarrowPrimitiveVisitor<String,
                        NanosyntaxParser.StringPrimitiveContext> {

    public NanoPrimitiveStringVisitor(NanoToASTVisitor master) {
        super(master, str -> str.replaceAll("^\"|\"$", ""));
    }
}
