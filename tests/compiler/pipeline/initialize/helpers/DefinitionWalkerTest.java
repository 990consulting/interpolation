/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTDefinitionNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.symbol.SymbolTable;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.BiConsumer;

import static org.mockito.Mockito.mock;

public class DefinitionWalkerTest {

    private BiConsumer<ASTValueNode, SymbolTable> walker;
    private ASTDefinitionNode input;
    private SymbolTable symbolTable;
    private DefinitionWalker query;

    @Before
    public void init() throws Exception {
        walker = mock(BiConsumer.class);
        input = mock(ASTDefinitionNode.class);
        symbolTable = mock(SymbolTable.class);

        query = new DefinitionWalker();
        query.init(walker);
    }

    @Test(expected = NotImplementedException.class)
    public void walkNotYetImplemented() throws Exception {
        query.walk(input, symbolTable);
    }
}