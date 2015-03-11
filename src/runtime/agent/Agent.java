/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent;

import runtime.geometry.coordinate.Coordinate;

import java.util.function.Supplier;

/**
 * Created by dbborens on 3/5/15.
 */
public class Agent {

    private final int id;
    private final Supplier<String> layerResolver;
    private final Supplier<Coordinate> locator;
    private final Runnable death;

    /**
     * @param id The agent's unique ID
     * @param layerResolver Reports the layer on which this agent resides
     * @param locator Reports the location of this agent
     * @param death Trigger that notifies scheduler and lattice to remove this
     *              agent, as well as notifying the neighborhood that the agent
     *              is gone
     */
    public Agent(int id, Supplier<String> layerResolver,
                 Supplier<Coordinate> locator, Runnable death) {

        this.id = id;
        this.layerResolver = layerResolver;
        this.locator = locator;
        this.death = death;
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

    public void die() {
        death.run();
    }
}
