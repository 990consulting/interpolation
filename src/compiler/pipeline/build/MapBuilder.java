/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build;

import com.google.common.collect.Sets;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.NestedContextSymbol;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.pipeline.translate.nodes.Resolvable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by dbborens on 3/13/15.
 */
public abstract class MapBuilder<T> implements Builder<MapObjectNode, T> {

    private final Map<String, Class> fields;
    private final Set<String> listeningFields;
    private final Map<String, Object> assignedFields;
    private Consumer<T> callback;

    public MapBuilder(Map<String, Object> assignedFields, Set<String> listeningFields) {
        this.assignedFields = assignedFields;
        this.listeningFields = listeningFields;
        fields = resolveFields();
        listeningFields.addAll(fields.keySet());
    }

    public MapBuilder() {
        this(new HashMap<>(), new HashSet<>());
    }

    @Override
    public void visit(MapObjectNode node, Consumer<T> callback) {
        this.callback = callback;
        Set<String> ids = (Set<String>) node.getMemberIdentifiers()
                .collect(Collectors.toSet());
        verifyFieldCoverage(ids);
        ids.forEach(id -> processMember(id, node));
    }

    private void processMember(String id, MapObjectNode node) {
        listeningFields.add(id);
        Consumer childCallback = obj -> resolve(id, obj);
        Resolvable resolvable = node.getMember(id);
        if (resolvable instanceof NestedContextSymbol) {
            ((NestedContextSymbol) resolvable).addListener(childCallback);
        } else if (resolvable instanceof ObjectNode) {
            ((ObjectNode) resolvable).instantiate(childCallback);
        } else {
            throw new IllegalStateException("Unrecognized Resolvable subclass "
                    + resolvable.getClass().getCanonicalName());
        }

    }
    private void instantiateIfPossible() {
        if (listeningFields.size() > 0) { return; }
        validateResolution();
        T result = instantiate();
        callback.accept(result);
    }

    /**
     * Sanity check--when this is called, there are no listening
     * fields, so every field should now be assigned. If it is
     * not, blow up.
     */
    private void validateResolution() {
        Set<String> p = fields.keySet();
        Set<String> q = assignedFields.keySet();
        if (Sets.symmetricDifference(p, q).size() > 0) {
            throw new IllegalStateException("Consistency failure: not all keys were loaded in object build.");
        }
    }

    private void verifyFieldCoverage(Set<String> ids) {
        Set<String> difference = Sets.symmetricDifference(ids, fields.keySet());
        if (difference.size() > 0) {
            throw new IllegalStateException("Not all fields are accounted for at construction time. " +
                    "Maybe values weren't interpolated from constraints?");
        }
    }

    private void resolve(String identifier, Object value) {
        // TODO: Replace generics in this hierarchy with TypeToken and add checks
        System.err.println("WARNING: values not type-checked on construction time.");

        // Remove value from listeningFields
        listeningFields.remove(identifier);

        // Assign value to fields
        assignedFields.put(identifier, value);

        // Attempt instantiation if there are no listening fields
        instantiateIfPossible();
    }

    protected abstract Map<String, Class> resolveFields();

    protected abstract T instantiate();
}
