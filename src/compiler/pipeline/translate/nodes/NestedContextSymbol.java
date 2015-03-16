/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/14/15.
 */
public class NestedContextSymbol<T, U> implements Resolvable {

    private final Function<T, U> function;
    private final List<Consumer<Supplier<U>>> listeners;
    private Supplier<U> supplier;

    /**
     * ReservedSymbol represents a built-in accessor associated with an
     * instance of a Nanoverse object. The specific instance is not resolved
     * until after construction. After the object is built, a supplier
     * representing the property's value is broadcast to any objects waiting
     * for it. Generally, these listeners point to mutators for other objects.
     *
     * @param function
     */
    public NestedContextSymbol(Function<T, U> function) {
        this.function = function;
        listeners = new ArrayList<>();
        supplier = null;
    }

    /**
     * Schedule a listener to be notified when a specific object is supplied
     * to this symbol. If the object has already been resolved,
     * then supply it immediately.
     *
     * @param listener
     */
    public void addListener(Consumer<Supplier<U>> listener) {
        if (resolved()) {
            listener.accept(supplier);
        }
        listeners.add(listener);
    }

    /**
     * Broadcast a supplier associated with a particular instance to
     * all listeners.
     *
     * @param instance
     */
    public void broadcast(T instance) {
        throwIfResolved();
        supplier = () -> function.apply(instance);
        listeners.stream()
                .forEach(listener ->
                        listener.accept(supplier));
    }

    private void throwIfResolved() {
        if (resolved()) {
            throw new IllegalStateException("Double dip on reserved keyword");
        }
    }

    private boolean resolved() {
        return supplier != null;
    }
}
