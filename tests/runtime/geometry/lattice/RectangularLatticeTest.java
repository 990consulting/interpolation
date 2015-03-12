/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.geometry.lattice;

import org.junit.Before;
import org.junit.Test;
import runtime.geometry.coordinate.Coordinate2D;
import test.TestBase;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class RectangularLatticeTest extends TestBase {

    private RectangularLattice query;

    @Before
    public void init() throws Exception {
        query = new RectangularLattice();
    }

    @Test
    public void getAnnulus0() throws Exception {
        Coordinate2D c = new Coordinate2D(1, 1);
        Set<Coordinate2D> expected = Stream.of(c)
                .collect(Collectors.toSet());

        Set<Coordinate2D> actual = query.getAnnulus(c, 0)
                .collect(Collectors.toSet());

        assertEquals(expected, actual);
    }

    @Test
    public void getAnnulus2() throws Exception {
        Coordinate2D c = new Coordinate2D(0, 0);

        Set<Coordinate2D> expected = Stream.of(
                new Coordinate2D(0, 2),
                new Coordinate2D(1, 1),
                new Coordinate2D(2, 0),
                new Coordinate2D(1, -1),
                new Coordinate2D(0, -2),
                new Coordinate2D(-1, -1),
                new Coordinate2D(-2, 0),
                new Coordinate2D(1, -1)
        ).collect(Collectors.toSet());

        Set<Coordinate2D> actual = query.getAnnulus(c, 2)
                .collect(Collectors.toSet());

        assertSetsEqual(expected, actual);
    }

    @Test
    public void invAdjustDoesNothing() throws Exception {
        Coordinate2D expected = new Coordinate2D(1, 1);
        Coordinate2D actual = query.invAdjust(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void adjustDoesNothing() throws Exception {
        Coordinate2D expected = new Coordinate2D(1, 1);
        Coordinate2D actual = query.adjust(expected);
        assertEquals(expected, actual);
    }
}