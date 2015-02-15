/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.nodes;

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

    public ValueNode getValue() {
        return value;
    }
}
