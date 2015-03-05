/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.symbol.ClassSymbolTable;
import compiler.symbol.InstanceSymbolTable;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListMemberResolverTest {

    @Test
    public void testResolve() throws Exception {
        ListObjectNode objectNode = mock(ListObjectNode.class);
        ClassSymbolTable cst = mock(ClassSymbolTable.class);
        when(objectNode.getSymbolTable()).thenReturn(cst);

        ASTValueNode value = mock(ASTValueNode.class);
        InstanceSymbolTable expected = mock(InstanceSymbolTable.class);
        when(cst.getSymbolTable(value)).thenReturn(expected);

        ListMemberResolver query = new ListMemberResolver(objectNode);
        InstanceSymbolTable actual = query.resolve(value);
        assertSame(expected, actual);
    }
}