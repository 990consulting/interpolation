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
import compiler.util.UnrecognizedIdentifierException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by dbborens on 2/18/15.
 */
public class ObjectSymbolTable extends SymbolTable<ASTStatementNode> {

    private LocalContext memberContext;

    public ObjectSymbolTable(TranslatorReferenceNode type) {
        super(type);
        memberContext = new LocalContext();
    }

    protected void member(String name, Symbol symbol) {
        memberContext.put(name, symbol);
    }

    @Override
    public TranslatorNode translate(ASTStatementNode content) {
        ASTAssignmentNode node = verifyAndCast(content);
        String name = node.getReference().getIdentifier();
        Symbol symbol;

        try {
            symbol = memberContext.get(name);
        } catch (UnrecognizedIdentifierException ex) {
            throw new IllegalArgumentException("Unrecognized argument '" + name + "' in object " + type);
        }

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
