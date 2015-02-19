/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.visitors;

import compiler.interpret.nodes.*;

import static compiler.interpret.nanosyntax.NanosyntaxParser.*;

/**
 * Created by dbborens on 2/14/15.
 */
public class SlaveNanoVisitorManager {

    private NanoAssignmentVisitor assignmentVisitor;
    private NanoBlockVisitor blockVisitor;
    private NanoOperandVisitor operandVisitor;
    private NanoOperationVisitor operationVisitor;
    private NanoOperatorVisitor operatorVisitor;
    private NanoPrimitiveVisitor primitiveVisitor;
    private NanoPrimitiveStringVisitor primitiveStringVisitor;
    private NanoPrimitiveIntVisitor primitiveIntVisitor;
    private NanoPrimitiveFloatVisitor primitiveFloatVisitor;
    private NanoReferenceVisitor referenceVisitor;
    private NanoRootVisitor rootVisitor;
    private NanoSingletonVisitor singletonVisitor;
    private NanoStatementVisitor statementVisitor;

    public void init(NanoToASTVisitor master) {
        assignmentVisitor      = new NanoAssignmentVisitor(master);
        blockVisitor           = new NanoBlockVisitor(master);
        operandVisitor         = new NanoOperandVisitor(master);
        operatorVisitor        = new NanoOperatorVisitor(master);
        operationVisitor       = new NanoOperationVisitor(master);
        primitiveVisitor       = new NanoPrimitiveVisitor(master);
        primitiveStringVisitor = new NanoPrimitiveStringVisitor(master);
        primitiveIntVisitor    = new NanoPrimitiveIntVisitor(master);
        primitiveFloatVisitor  = new NanoPrimitiveFloatVisitor(master);
        referenceVisitor       = new NanoReferenceVisitor(master);
        rootVisitor            = new NanoRootVisitor(master);
        singletonVisitor       = new NanoSingletonVisitor(master);
        statementVisitor       = new NanoStatementVisitor(master);
    }

    public ASTRootNode visit(RootContext ctx) {
        return rootVisitor.visit(ctx);
    }

    public ASTStatementNode visit(StatementContext ctx) {
        return statementVisitor.visit(ctx);
    }

    public ASTAssignmentNode visit(AssignmentContext ctx) {
        return assignmentVisitor.visit(ctx);
    }

    public ASTBlockNode visit(BlockContext ctx) {
        return blockVisitor.visit(ctx);
    }

    public ASTValueNode visit(SingletonContext ctx) {
        return singletonVisitor.visit(ctx);
    }

    public ASTOperationNode visit(OperationContext ctx) {
        return operationVisitor.visit(ctx);
    }

    public ASTPrimitiveNode<String> visit(OperatorContext ctx) {
        return operatorVisitor.visit(ctx);
    }

    public ASTValueNode visit(OperandContext ctx) {
        return operandVisitor.visit(ctx);
    }

    public ASTReferenceNode visit(ReferenceContext ctx) {
        return referenceVisitor.visit(ctx);
    }

    public ASTPrimitiveNode visit(PrimitiveContext ctx) {
        return primitiveVisitor.visit(ctx);
    }

    public ASTPrimitiveNode<String> visit(StringPrimitiveContext ctx) {
        return primitiveStringVisitor.visit(ctx);
    }

    public ASTPrimitiveNode<Double> visit(FloatPrimitiveContext ctx) {
        return primitiveFloatVisitor.visit(ctx);
    }

    public ASTPrimitiveNode<Integer> visit(IntPrimitiveContext ctx) {
        return primitiveIntVisitor.visit(ctx);
    }
}
