/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.*;
import compiler.symbol.SymbolTable;
import compiler.util.UserError;

import java.util.function.BiConsumer;

/**
 * Created by dbborens on 2/22/15.
 */
public class SlaveManager {

    private AssignmentWalker assignmentWalker;
    private BlockWalker blockWalker;
    private DefinitionWalker definitionWalker;
    private ReferenceWalker referenceWalker;

    public SlaveManager() {
        assignmentWalker = new AssignmentWalker();
        blockWalker = new BlockWalker();
        definitionWalker = new DefinitionWalker();
        referenceWalker = new ReferenceWalker();
    }

    public SlaveManager(AssignmentWalker assignmentWalker,
                        BlockWalker blockWalker,
                        DefinitionWalker definitionWalker,
                        ReferenceWalker referenceWalker) {

        this.assignmentWalker = assignmentWalker;
        this.blockWalker = blockWalker;
        this.definitionWalker = definitionWalker;
        this.referenceWalker = referenceWalker;
    }

    public void walk(ASTAssignmentNode toWalk, SymbolTable symbolTable) throws UserError {
        assignmentWalker.walk(toWalk, symbolTable);
    }

    public void walk(ASTBlockNode toWalk, SymbolTable symbolTable) {
        blockWalker.walk(toWalk, symbolTable);

    }

    public void walk(ASTDefinitionNode toWalk, SymbolTable symbolTable) {
        definitionWalker.walk(toWalk, symbolTable);

    }

    public void walk(ASTReferenceNode toWalk, SymbolTable symbolTable) throws UserError {
        referenceWalker.walk(toWalk, symbolTable);
    }

    public void init(BiConsumer<ASTNode, SymbolTable> walker) {
        assignmentWalker.init(walker);
        blockWalker.init(walker);
        definitionWalker.init(walker);
        referenceWalker.init(walker);
    }
}
