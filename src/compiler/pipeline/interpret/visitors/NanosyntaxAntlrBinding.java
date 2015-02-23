/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.AntlrBinding;
import compiler.pipeline.interpret.nodes.ASTNode;

/**
 * Created by dbborens on 2/21/15.
 */
public class NanosyntaxAntlrBinding extends AntlrBinding<ASTNode> {

    public NanosyntaxAntlrBinding() {
        super(new NanoToASTVisitor());
    }

}
