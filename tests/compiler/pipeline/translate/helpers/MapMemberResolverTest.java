/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.helpers;

import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.InstantiableSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MapMemberResolverTest {

    private MapMemberResolver query;
    private MapObjectNode objectNode;
    private ASTAssignmentNode translateNode;

    @Before
    public void init() throws Exception {
        objectNode = mock(MapObjectNode.class);

        ASTReferenceNode ref = new ASTReferenceNode("test");
        ASTValueNode value = mock(ASTValueNode.class);
        translateNode = new ASTAssignmentNode(ref, value);

        query = new MapMemberResolver(objectNode);
    }

    @Test
    public void resolveAsksParentSymbolTable() throws Exception {
        ClassSymbolTable classTable = mock(ClassSymbolTable.class);
        when(objectNode.getSymbolTable("test")).thenReturn(classTable);

        ASTValueNode value = translateNode.getValue();
        MapSymbolTable expected = mock(MapSymbolTable.class);
        when(classTable.getSymbolTable(value)).thenReturn(expected);

        InstantiableSymbolTable actual = query.resolve(translateNode);
        assertSame(expected, actual);
    }
}