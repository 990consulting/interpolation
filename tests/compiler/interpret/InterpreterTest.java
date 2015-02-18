/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret;

import compiler.interpret.nodes.ASTNode;
import compiler.interpret.nodes.ASTRootNode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InterpreterTest {

    private ASTRootNode rootNode;
    private File file;
    private Interpreter query;

    @Before
    public void init() {
        AntlrBinding<ASTNode> antlr = mock(AntlrBinding.class);
        file = mock(File.class);
        when(file.exists()).thenReturn(true);
        rootNode = mock(ASTRootNode.class);
        when(antlr.interpret(file)).thenReturn(rootNode);
        query = new Interpreter(antlr);
    }

    @Test
    public void interpretReturnsRootNode() {
        ASTNode actual = query.interpret(file);
        assertSame(rootNode, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullFileThrows() {
        query.interpret(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonExistentFileThrows() {
        file = mock(File.class);
        when(file.exists()).thenReturn(false);
        query.interpret(file);
    }
}