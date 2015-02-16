/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation;

import compiler.nodes.BaseNode;
import compiler.nodes.RootNode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InterpreterTest {

    private RootNode rootNode;
    private File file;
    private Interpreter query;

    @Before
    public void init() {
        AntlrBinding<BaseNode> antlr = mock(AntlrBinding.class);
        file = mock(File.class);
        when(file.exists()).thenReturn(true);
        rootNode = mock(RootNode.class);
        when(antlr.interpret(file)).thenReturn(rootNode);
        query = new Interpreter(antlr);
    }

    @Test
    public void interpretReturnsRootNode() {
        BaseNode actual = query.interpret(file);
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