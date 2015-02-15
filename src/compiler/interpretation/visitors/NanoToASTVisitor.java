/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import compiler.interpretation.nanosyntax.NanosyntaxParser;
import compiler.nodes.*;
import org.antlr.v4.runtime.misc.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import compiler.interpretation.nanosyntax.NanosyntaxBaseVisitor;
/**
 * Created by dbborens on 2/14/15.
 */
public class NanoToASTVisitor extends NanosyntaxBaseVisitor<BaseNode> {
    @Override
    public RootNode visitRoot(@NotNull NanosyntaxParser.RootContext ctx) {
        return null;
    }

    @Override
    public StatementNode visitStatement(@NotNull NanosyntaxParser.StatementContext ctx) {
        return null;
    }

    @Override
    public AssignmentNode visitAssignment(@NotNull NanosyntaxParser.AssignmentContext ctx) {
        AssignmentVisitor visitor = new AssignmentVisitor(this);
        visitor.visit(ctx);
        return null;
    }

    @Override
    public DefinitionNode visitDefinition(@NotNull NanosyntaxParser.DefinitionContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public BlockNode visitBlock(@NotNull NanosyntaxParser.BlockContext ctx) {
        return null;
    }

    @Override
    public ValueNode visitSingleton(@NotNull NanosyntaxParser.SingletonContext ctx) {
        return null;
    }

    @Override
    public OperationNode visitOperation(@NotNull NanosyntaxParser.OperationContext ctx) {
        return null;
    }

    @Override
    public PrimitiveNode<String> visitOperand(@NotNull NanosyntaxParser.OperandContext ctx) {
        return null;
    }

    @Override
    public ValueNode visitOperator(@NotNull NanosyntaxParser.OperatorContext ctx) {
        return null;
    }

    @Override
    public ReferenceNode visitReference(@NotNull NanosyntaxParser.ReferenceContext ctx) {
        return null;
    }

    @Override
    public BaseNode visitType(@NotNull NanosyntaxParser.TypeContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public PrimitiveNode visitPrimitive(@NotNull NanosyntaxParser.PrimitiveContext ctx) {
        return null;
    }

    @Override
    public PrimitiveNode<String> visitStringPrimitive(@NotNull NanosyntaxParser.StringPrimitiveContext ctx) {
        return null;
    }

    @Override
    public PrimitiveNode<Double> visitFloatPrimitive(@NotNull NanosyntaxParser.FloatPrimitiveContext ctx) {
        return null;
    }

    @Override
    public PrimitiveNode<Integer> visitIntPrimitive(@NotNull NanosyntaxParser.IntPrimitiveContext ctx) {
        return null;
    }
}
