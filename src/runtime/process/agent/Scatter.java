/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.process.agent;

import runtime.agent.AgentEstablisher;
import runtime.geometry.coordinate.Coordinate;
import runtime.util.RandomChooser;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Scatter creates a population of agents, based on a specified
 * description, placed randomly within a specified region of space.
 *
 * Created by dbborens on 3/12/15.
 */
public class Scatter extends AgentProcess {

    private AgentEstablisher producer;
    private Supplier<Stream<Coordinate>> siteSupplier;
    private RandomChooser<Coordinate> chooser;
    private int count;

    /**
     *
     * @param producer AgentProducer for the type of agent
     *                 to be scattered.
     *
     * @param siteSupplier Provides a stream of coordinates
     *                     representing valid sites for this
     *                     scatter operation.
     *
     * @param chooser Random coordinate selector.
     *
     * @param count Number of agents to scatter.
     */
    public Scatter(AgentEstablisher producer,
                   Supplier<Stream<Coordinate>> siteSupplier,
                   RandomChooser<Coordinate> chooser,
                   int count) {

        this.producer = producer;
        this.siteSupplier = siteSupplier;
        this.chooser = chooser;
        this.count = count;
    }

    @Override
    public void run() {
        Stream<Coordinate> candidates = siteSupplier.get();
        Stream<Coordinate> targets = chooser.choose(candidates, count);
        targets.forEach(producer::establish);
    }
}
