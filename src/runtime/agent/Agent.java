/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent;

import runtime.geometry.Coordinate;

import java.util.function.Supplier;

/**
 * Created by dbborens on 3/5/15.
 */
public class Agent {

    private final int id;
    private final Supplier<String> layerResolver;
    private final Supplier<Coordinate> locator;

    public Agent(int id, Supplier<String> layerResolver,
                 Supplier<Coordinate> locator) {

        this.id = id;
        this.layerResolver = layerResolver;
        this.locator = locator;
    }

    public int getAgentId() {
        return id;
    }

    public Coordinate locate() {
        return locator.get();
    }

    public String getLayerId() {
        return layerResolver.get();
    }
}
