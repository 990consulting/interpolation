/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxBaseVisitor;
import compiler.pipeline.interpret.nodes.*;
import org.antlr.v4.runtime.misc.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
/**
 * Created by dbborens on 2/14/15.
 */
public class NanoToASTVisitor extends NanosyntaxBaseVisitor<ASTNode> {

    private SlaveNanoVisitorManager manager;

    public NanoToASTVisitor() {
        this(new SlaveNanoVisitorManager());
    }

    public NanoToASTVisitor(SlaveNanoVisitorManager manager) {
        manager.init(this);
        this.manager = manager;
    }

    @Override
    public ASTRootNode visitRoot(@NotNull RootContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTStatementNode visitStatement(@NotNull StatementContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTAssignmentNode visitAssignment(@NotNull AssignmentContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTDefinitionNode visitDefinition(@NotNull DefinitionContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTBlockNode visitBlock(@NotNull BlockContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTValueNode visitSingleton(@NotNull SingletonContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTAssignmentNode visitOperation(@NotNull OperationContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTValueNode visitOperand(@NotNull OperandContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTReferenceNode visitOperator(@NotNull OperatorContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTReferenceNode visitReference(@NotNull ReferenceContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTNode visitType(@NotNull TypeContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTPrimitiveNode visitPrimitive(@NotNull PrimitiveContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTPrimitiveNode<String> visitStringPrimitive(@NotNull StringPrimitiveContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTPrimitiveNode<Double> visitFloatPrimitive(@NotNull FloatPrimitiveContext ctx) {
        return manager.visit(ctx);
    }

    @Override
    public ASTPrimitiveNode<Integer> visitIntPrimitive(@NotNull IntPrimitiveContext ctx) {
        return manager.visit(ctx);
    }
}
