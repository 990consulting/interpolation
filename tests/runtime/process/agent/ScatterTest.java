/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.process.agent;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.AgentEstablisher;
import runtime.geometry.coordinate.Coordinate;
import runtime.util.RandomChooser;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class ScatterTest {

    private AgentEstablisher producer;
    private Supplier<Stream<Coordinate>> siteSupplier;
    private RandomChooser<Coordinate> chooser;
    private int count;
    private Scatter query;

    @Before
    public void init() throws Exception {
        producer = mock(AgentEstablisher.class);
        siteSupplier = mock(Supplier.class);
        chooser = mock(RandomChooser.class);
        count = 3;
        query = new Scatter(producer, siteSupplier, chooser, count);
    }

    @Test
    public void runEstablishesEachTarget() throws Exception {
        Stream<Coordinate> candidates = mock(Stream.class);
        when(siteSupplier.get()).thenReturn(candidates);
        Coordinate target = mock(Coordinate.class);
        Stream<Coordinate> targets = Stream.of(target);
        when(chooser.choose(candidates, count)).thenReturn(targets);
        query.run();
        verify(producer, times(1)).establish(target);
    }
}