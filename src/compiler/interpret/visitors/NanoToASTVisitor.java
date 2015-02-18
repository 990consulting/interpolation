/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nanosyntax.NanosyntaxBaseVisitor;
import compiler.interpret.nodes.*;
import org.antlr.v4.runtime.misc.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;
/**
 * Created by dbborens on 2/14/15.
 */
public class NanoToASTVisitor extends NanosyntaxBaseVisitor<ASTNode> {

    private SlaveNanoVisitorManager manager;

    public NanoToASTVisitor(SlaveNanoVisitorManager manager) {
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
    public ASTNode visitType(@NotNull TypeContext ctx) {
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
