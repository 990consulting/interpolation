/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.util.UnrecognizedIdentifierError;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class ClassSymbolTableTest {

    private InstanceSymbolTable ist;
    private LoadedClassSymbolTable query;

    @Before
    public void init() throws Exception {
        ist = mock(InstanceSymbolTable.class);
        query = new LoadedClassSymbolTable();
    }

    @Test
    public void referenceCase() throws Exception {
        ASTReferenceNode node = new ASTReferenceNode("test");
        InstanceSymbolTable actual = query.getSymbolTable(node);
        assertSame(ist, actual);
    }

    @Test
    public void assignmentCase() throws Exception {
        ASTReferenceNode ref = new ASTReferenceNode("test");
        ASTAssignmentNode node = new ASTAssignmentNode(ref, null);
        InstanceSymbolTable actual = query.getSymbolTable(node);
        assertSame(ist, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalInputThrows() throws Exception {
        ASTValueNode node = mock(ASTValueNode.class);
        query.getSymbolTable(node);
    }

    @Test(expected = UnrecognizedIdentifierError.class)
    public void unrecognizedSubclassThrows() throws Exception {
        ASTReferenceNode node = new ASTReferenceNode("something unexpected");
        query.getSymbolTable(node);
    }

    private class LoadedClassSymbolTable extends ClassSymbolTable {

        @Override
        protected HashMap<String, ClassSymbol> resolveSubclasses() {
            HashMap<String, ClassSymbol> ret = new HashMap<>(1);
            ClassSymbol symbol = new ClassSymbol(() -> ist, "");
            ret.put("test", symbol);
            return ret;
        }
    }
}