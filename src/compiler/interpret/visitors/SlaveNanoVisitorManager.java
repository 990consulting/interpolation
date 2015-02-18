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

    private ASTAssignmentVisitor assignmentVisitor;
    private ASTBlockVisitor blockVisitor;
    private ASTOperandVisitor operandVisitor;
    private ASTOperationVisitor operationVisitor;
    private ASTOperatorVisitor operatorVisitor;
    private ASTPrimitiveVisitor primitiveVisitor;
    private ASTPrimitiveStringVisitor primitiveStringVisitor;
    private ASTPrimitiveIntVisitor primitiveIntVisitor;
    private ASTPrimitiveFloatVisitor primitiveFloatVisitor;
    private ASTReferenceVisitor referenceVisitor;
    private ASTRootVisitor rootVisitor;
    private ASTSingletonVisitor singletonVisitor;
    private ASTStatementVisitor statementVisitor;

    public void init(NanoToASTVisitor master) {
        assignmentVisitor      = new ASTAssignmentVisitor(master);
        blockVisitor           = new ASTBlockVisitor(master);
        operandVisitor         = new ASTOperandVisitor(master);
        operatorVisitor        = new ASTOperatorVisitor(master);
        operationVisitor       = new ASTOperationVisitor(master);
        primitiveVisitor       = new ASTPrimitiveVisitor(master);
        primitiveStringVisitor = new ASTPrimitiveStringVisitor(master);
        primitiveIntVisitor    = new ASTPrimitiveIntVisitor(master);
        primitiveFloatVisitor  = new ASTPrimitiveFloatVisitor(master);
        referenceVisitor       = new ASTReferenceVisitor(master);
        rootVisitor            = new ASTRootVisitor(master);
        singletonVisitor       = new ASTSingletonVisitor(master);
        statementVisitor       = new ASTStatementVisitor(master);
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
