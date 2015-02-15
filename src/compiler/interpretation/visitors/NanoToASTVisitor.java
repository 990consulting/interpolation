/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;
import compiler.nodes.*;
import org.antlr.v4.runtime.misc.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import compiler.interpretation.nanosyntax.NanosyntaxBaseVisitor;
/**
 * Created by dbborens on 2/14/15.
 */
public class NanoToASTVisitor extends NanosyntaxBaseVisitor<BaseNode> {

    private SlaveVisitorManager manager;

    public NanoToASTVisitor(SlaveVisitorManager manager) {
        manager.init(this);
        this.manager = manager;
    }

    @Override
    public RootNode visitRoot(@NotNull RootContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public StatementNode visitStatement(@NotNull StatementContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public AssignmentNode visitAssignment(@NotNull AssignmentContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public DefinitionNode visitDefinition(@NotNull DefinitionContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public BlockNode visitBlock(@NotNull BlockContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ValueNode visitSingleton(@NotNull SingletonContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public OperationNode visitOperation(@NotNull OperationContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ValueNode visitOperand(@NotNull OperandContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public PrimitiveNode<String> visitOperator(@NotNull OperatorContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ReferenceNode visitReference(@NotNull ReferenceContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public BaseNode visitType(@NotNull TypeContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public PrimitiveNode visitPrimitive(@NotNull PrimitiveContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public PrimitiveNode<String> visitStringPrimitive(@NotNull StringPrimitiveContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public PrimitiveNode<Double> visitFloatPrimitive(@NotNull FloatPrimitiveContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public PrimitiveNode<Integer> visitIntPrimitive(@NotNull IntPrimitiveContext ctx) {
        return manager.visit(ctx);
    }
}
