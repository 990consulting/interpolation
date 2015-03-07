/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

import org.junit.*;
import runtime.geometry.Coordinate;
import runtime.util.RandomChooser;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WanderTest {

    private RandomChooser chooser;
    private Supplier<Stream<Coordinate>> neighborhood;
    private Consumer<Coordinate> mover;
    private Coordinate c;
    private Wander query;

    @Before
    public void init() throws Exception {
        neighborhood = mock(Supplier.class);
        Stream<Coordinate> stream = mock(Stream.class);
        when(neighborhood.get()).thenReturn(stream);

        chooser = mock(RandomChooser.class);
        c = mock(Coordinate.class);
        when(chooser.apply(stream)).thenReturn(c);

        mover = mock(Consumer.class);
        query = new Wander(chooser, neighborhood, mover);
    }

    @Test
    public void testRun() throws Exception {
        query.run();
        verify(mover).accept(c);
    }
}