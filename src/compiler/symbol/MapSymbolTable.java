/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol;

import compiler.nodes.TypeNode;
import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.util.IllegalAssignmentError;
import compiler.util.SyntaxError;
import compiler.util.UnrecognizedIdentifierError;
import compiler.util.UserError;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by dbborens on 2/22/15.
 */
public abstract class MapSymbolTable implements SymbolTable {

    private LocalContext localContext;
    private ReservedContext reservedContext;
    private TypeNode type;

    public MapSymbolTable(ReservedContext parentReserved) {
        localContext = resolveLocalContext();
        reservedContext = resolveReservedContext(parentReserved);
        type = resolveType();
    }

    protected abstract LocalContext resolveLocalContext();
    protected abstract ReservedContext resolveReservedContext(ReservedContext parent);
    protected abstract TypeNode resolveType();

    @Override
    public TypeNode getType() {
        return type;
    }

    @Override
    public void initSymbolTable(ASTValueNode content) throws UserError {
        ASTAssignmentNode assignment = verifyAndCast(content);
        String identifier = assignment.getReference().getIdentifier();

        if (reservedContext.has(identifier)) {
            throw new IllegalAssignmentError();
        }

        if (!localContext.has(identifier)) {
            throw new UnrecognizedIdentifierError();
        }

        ASTValueNode value = assignment.getValue();
        localContext.get(identifier).resolve(value);
    }

    private ASTAssignmentNode verifyAndCast(ASTValueNode content) throws UserError {
        if (!(content instanceof ASTAssignmentNode)) {
            throw new SyntaxError("Non-assignment in object definition");
        }

        ASTAssignmentNode ret = (ASTAssignmentNode) content;

        if (ret.getReference().hasChild()) {
            throw new NotImplementedException();
        }

        return ret;
    }
    @Override
    public SymbolTable getSymbolTable(ASTValueNode content) throws UserError {
        ASTAssignmentNode assignment = verifyAndCast(content);
        String identifier = assignment.getReference().getIdentifier();

        if (reservedContext.has(identifier)) {
            throw new IllegalStateException("Attempted to retrieve symbol table for reserved keyword.");
        }

        if (!localContext.has(identifier)) {
            throw new UnrecognizedIdentifierError();
        }

        return localContext.get(identifier).getSymbolTable();
    }
}
