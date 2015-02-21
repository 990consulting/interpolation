/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.visitors;

import compiler.interpret.nodes.*;
import compiler.interpret.visitors.AbstractNanoBlockVisitor;
import compiler.nodes.AbstractReferenceNode;
import compiler.translate.nodes.TranslatorObjectNode;
import compiler.translate.nodes.TranslatorObjectNodeBuilder;
import compiler.translate.symbols.SymbolTable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Supplier;

/**
 * An object symbol table is a symbol table with a defined set of
 * named members. The object corresponds to a member of a predetermined type.
 * The following rules apply:
 *
 * (1) The content must consist of zero or more statements. (A statement is
 *     an assignment or a definition.)
 * (2) The referent of any assignment must be a named member.
 * (3) The value of any assignment must be an object or an operation.
 * (4) If the value of the assignment is an object, it must have the
 *     object type expected by the referent.
 * (5) If the value of the assignment is an operation, it must have
 *     return type corresponding to the type expected by the referent.
 *
 * The return type of this ObjectSymbolTable is a TranslatorObjectNode.
 *
 * Created by dbborens on 2/18/15.
 */
public class ASTToObjectVisitor implements ASTVisitor<ASTValueNode> {

    private Supplier<TranslatorObjectNode> supplier;
    private ASTObjAssignmentVisitor asnVisitor;
    private ASTObjBlockVisitor blkVisitor;

    public ASTToObjectVisitor(SymbolTable symbolTable) {
        TranslatorObjectNodeBuilder builder = new TranslatorObjectNodeBuilder(symbolTable.getType());
        supplier = () -> builder.build();
        asnVisitor = new ASTObjAssignmentVisitor(symbolTable, builder);
        blkVisitor = new ASTObjBlockVisitor(asnVisitor);
    }

    public ASTToObjectVisitor(ASTObjAssignmentVisitor asnVisitor,
                              ASTObjBlockVisitor blkVisitor,
                              Supplier<TranslatorObjectNode> supplier) {
        this.asnVisitor = asnVisitor;
        this.blkVisitor = blkVisitor;
        this.supplier = supplier;
    }

    @Override
    public TranslatorObjectNode visit(ASTValueNode toVisit) {
        doVisit(toVisit);
        return supplier.get();
    }

    private void doVisit(ASTValueNode toVisit) {
        if (toVisit instanceof ASTAssignmentNode) {
            asnVisitor.visit((ASTAssignmentNode) toVisit);
        } else if (toVisit instanceof ASTBlockNode) {
            blkVisitor.visit((ASTBlockNode) toVisit);
        } else if (toVisit instanceof ASTDefinitionNode) {
            throw new NotImplementedException();
        } else {
            throw new IllegalArgumentException("Unexpected argument in object node");
        }
    }

}
