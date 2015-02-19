/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.translate.symbols;

import compiler.interpret.nodes.ASTAssignmentNode;
import compiler.interpret.nodes.ASTDefinitionNode;
import compiler.interpret.nodes.ASTStatementNode;
import compiler.translate.nodes.TranslatorNode;
import compiler.translate.nodes.TranslatorReferenceNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

/**
 * Created by dbborens on 2/18/15.
 */
public class ObjectSymbolTable extends SymbolTable<ASTStatementNode> {

    private HashMap<String, Symbol> members;

    public ObjectSymbolTable(TranslatorReferenceNode type) {
        super(type);
        members = new HashMap<>();
    }

    protected void member(String name, Symbol symbol) {
        members.put(name, symbol);
    }

    @Override
    public TranslatorNode translate(ASTStatementNode content) {
        ASTAssignmentNode node = verifyAndCast(content);
        String name = node.getReference().getName();
        if (!members.containsKey(name)) {
            throw new IllegalArgumentException("Unrecognized argument '" + name + "' in object " + type);
        }
        Symbol symbol = members.get(name);
        TranslationHelper translator = symbol.getTranslator();
        return translator.translate(node);
    }


    private ASTAssignmentNode verifyAndCast(ASTStatementNode toCast) {
        if (toCast instanceof ASTDefinitionNode) {
            throw new NotImplementedException();
        } else if (!(toCast instanceof ASTAssignmentNode)) {
            throw new IllegalStateException("Unexpected argument in object node");
        }

        return (ASTAssignmentNode) toCast;
    }
}
