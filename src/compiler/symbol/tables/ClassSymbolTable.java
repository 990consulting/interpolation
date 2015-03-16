/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import com.google.common.reflect.TypeToken;
import compiler.pipeline.interpret.nodes.ASTAssignmentNode;
import compiler.pipeline.interpret.nodes.ASTReferenceNode;
import compiler.pipeline.interpret.nodes.ASTValueNode;
import compiler.symbol.symbols.ClassSymbol;
import compiler.util.UnrecognizedIdentifierError;

import java.util.HashMap;

/**
 * Created by dbborens on 3/3/15.
 */
public abstract class ClassSymbolTable<T> implements ResolvingSymbolTable {

    private HashMap<String, ClassSymbol> members;

    private final TypeToken<T> type = new TypeToken<T>(getClass()) {};

    public ClassSymbolTable() {
        members = resolveSubclasses();
    }

    protected abstract HashMap<String, ClassSymbol> resolveSubclasses();

    @Override
    public InstantiableSymbolTable getSymbolTable(ASTValueNode value) {
        String identifier;
        if (value instanceof ASTReferenceNode) {
            identifier = ((ASTReferenceNode) value).getIdentifier();
        } else if (value instanceof ASTAssignmentNode) {
            identifier = ((ASTAssignmentNode) value).getReference().getIdentifier();
        } else {
            throw new IllegalArgumentException("Unrecognized class specifier");
        }

        return doGet(identifier);
    }

    private InstantiableSymbolTable doGet(String identifier) {
        if (!members.containsKey(identifier)) {
            throw new UnrecognizedIdentifierError();
        }

        return members.get(identifier).getSymbolTable();
    }

    public Class getBroadClass() {
        return type.getRawType();
    }
}
