/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.interpret.nodes;

import compiler.nodes.Node;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/13/15.
 */
public class DefinitionNode implements StatementNode {

    String name;
    String type;
    ValueNode value;

    public DefinitionNode(String name, String type, ValueNode value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public Stream<? extends ASTNode> getChildren() {
        return Stream.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefinitionNode that = (DefinitionNode) o;

        if (!name.equals(that.name)) return false;
        if (!type.equals(that.type)) return false;
        if (!value.equals(that.value)) return false;

        return true;
    }

    @Override
    public ASTNode withNewChildren(Stream<? extends ASTNode> children) {
        List<Node> childList = children.collect(Collectors.<Node>toList());
        if (childList.size() != 1) {
            throw new IllegalStateException("Unexpected child count");
        }

        if (!(childList.get(0) instanceof ValueNode)) {
            throw new IllegalStateException("Illegal node for definition value.");
        }

        ValueNode newValNode = (ValueNode) childList.get(0);

        return new DefinitionNode(name, type, newValNode);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
