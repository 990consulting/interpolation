/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions.coordinate;

import org.junit.*;
import runtime.geometry.Coordinate;

import javax.management.Query;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NeighborhoodSupplierTest {

    @Test
    public void testGet() throws Exception {
        Function<Coordinate, Stream<Coordinate>> coordToNeighborhood = mock(Function.class);
        Supplier<Coordinate> locator = mock(Supplier.class);

        Coordinate c = mock(Coordinate.class);
        when(locator.get()).thenReturn(c);

        Stream<Coordinate> expected = mock(Stream.class);
        when(coordToNeighborhood.apply(c)).thenReturn(expected);

        NeighborhoodSupplier query = new NeighborhoodSupplier(coordToNeighborhood, locator);
        Stream<Coordinate> actual = query.get();

        assertSame(expected, actual);
    }
}