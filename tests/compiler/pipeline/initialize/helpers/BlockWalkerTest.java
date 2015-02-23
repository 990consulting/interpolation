/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.initialize.helpers;

import compiler.pipeline.interpret.nodes.ASTBlockNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.nodes.ASTStatementNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.symbol.SymbolTable;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class BlockWalkerTest {

    private BiConsumer<ASTNode, SymbolTable> walker;
    private ASTBlockNode input;
    private ASTStatementNode child;
    private SymbolTable symbolTable;

    private BlockWalker query;

    @Before
    public void init() throws Exception {
        walker = mock(BiConsumer.class);
        input = mock(ASTBlockNode.class);
        child = mock(ASTStatementNode.class);
        symbolTable = mock(SymbolTable.class);
        Stream<ASTValueNode> childStream = Stream.of(child);
        when(input.getChildren()).thenReturn(childStream);

        query = new BlockWalker();
        query.init(walker);
    }

    @Test
    public void walkReachesChildren() throws Exception {
        query.walk(input, symbolTable);
        verify(walker).accept(child, symbolTable);
    }
}