/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTValueNode;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorReferenceNode;
import compiler.translate.visitors.ASTVisitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TranslationHelperTest {

    private TranslatorNode expected;

    @Before
    public void init() throws Exception {
        expected = mock(TranslatorNode.class);
    }

    @Test
    public void translateAsksVisitor() {
        TranslatorNode actual = runTest(true);
        assertSame(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongReturnTypeThrows() {
        runTest(false);
    }

    private TranslatorNode runTest(boolean rightType) {
        ASTAssignmentNode input = mock(ASTAssignmentNode.class);

        ASTValueNode value = mock(ASTValueNode.class);
        when(input.getValue()).thenReturn(value);

        TranslatorReferenceNode expectedType = new TranslatorReferenceNode("yes");
        TranslatorReferenceNode actualType = rightType ? expectedType : new TranslatorReferenceNode("no");

        when(expected.getType()).thenReturn(actualType);

        ASTVisitor visitor = mock(ASTVisitor.class);
        when(visitor.visit(value)).thenReturn(expected);

        TranslationHelper query = new TranslationHelper(expectedType, visitor);
        return query.translate(input);
    }

}