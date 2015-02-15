/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpretation.visitors;
import com.sun.prism.impl.BaseContext;
import compiler.nodes.*;

import static compiler.interpretation.nanosyntax.NanosyntaxParser.*;

/**
 * Created by dbborens on 2/14/15.
 */
public class SlaveVisitorManager {

    private AssignmentVisitor assignmentVisitor;
    private BlockVisitor      blockVisitor;
    private OperandVisitor    operandVisitor;
    private OperationVisitor  operationVisitor;
    private OperatorVisitor   operatorVisitor;
    private PrimitiveVisitor  primitiveVisitor;
    private ReferenceVisitor  referenceVisitor;
    private RootVisitor       rootVisitor;
    private SingletonVisitor  singletonVisitor;
    private StatementVisitor  statementVisitor;

    public void init(NanoToASTVisitor master) {
        assignmentVisitor = new AssignmentVisitor(master);
        blockVisitor      = new BlockVisitor(master);
        operandVisitor    = new OperandVisitor(master);
        operatorVisitor   = new OperatorVisitor(master);
        operationVisitor  = new OperationVisitor(master);
        primitiveVisitor  = new PrimitiveVisitor(master);
        referenceVisitor  = new ReferenceVisitor(master);
        rootVisitor       = new RootVisitor(master);
        singletonVisitor  = new SingletonVisitor(master);
        statementVisitor  = new StatementVisitor(master);
    }

    public RootNode visit(RootContext ctx) {
        return rootVisitor.visit(ctx);
    }

    public StatementNode visit(StatementContext ctx) {
        return statementVisitor.visit(ctx);
    }

    public AssignmentNode visit(AssignmentContext ctx) {
        return assignmentVisitor.visit(ctx);
    }

    public BlockNode visit(BlockContext ctx) {
        return blockVisitor.visit(ctx);
    }

    public ValueNode visit(SingletonContext ctx) {
        return singletonVisitor.visit(ctx);
    }

    public OperationNode visit(OperationContext ctx) {
        return operationVisitor.visit(ctx);
    }

    public PrimitiveNode<String> visit(OperatorContext ctx) {
        return operatorVisitor.visit(ctx);
    }

    public ValueNode visit(OperandContext ctx) {
        return operandVisitor.visit(ctx);
    }

    public ReferenceNode visit(ReferenceContext ctx) {
        return referenceVisitor.visit(ctx);
    }

    public PrimitiveNode visit(PrimitiveContext ctx) {
        return primitiveVisitor.visit(ctx);
    }

    public PrimitiveNode<String> visit(StringPrimitiveContext ctx) {
        return primitiveVisitor.visit(ctx);
    }

    public PrimitiveNode<Double> visit(FloatPrimitiveContext ctx) {
        return primitiveVisitor.visit(ctx);
    }

    public PrimitiveNode<Integer> visit(IntPrimitiveContext ctx) {
        return primitiveVisitor.visit(ctx);
    }
//    public AssignmentVisitor getAssignmentVisitor() {
//        return assignmentVisitor;
//    }
//
//    public BlockVisitor getBlockVisitor() {
//        return blockVisitor;
//    }
//
//    public OperandVisitor getOperandVisitor() {
//        return operandVisitor;
//    }
//
//    public OperationVisitor getOperationVisitor() {
//        return operationVisitor;
//    }
//
//    public OperatorVisitor getOperatorVisitor() {
//        return operatorVisitor;
//    }
//
//    public PrimitiveVisitor getPrimitiveVisitor() {
//        return primitiveVisitor;
//    }
//
//    public ReferenceVisitor getReferenceVisitor() {
//        return referenceVisitor;
//    }
//
//    public RootVisitor getRootVisitor() {
//        return rootVisitor;
//    }
//
//    public SingletonVisitor getSingletonVisitor() {
//        return singletonVisitor;
//    }
//
//    public StatementVisitor getStatementVisitor() {
//        return statementVisitor;
//    }
}
