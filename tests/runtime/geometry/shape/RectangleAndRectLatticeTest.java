/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.geometry.shape;

import org.junit.Before;
import org.junit.Test;
import runtime.geometry.coordinate.Coordinate;
import runtime.geometry.coordinate.Coordinate2D;
import runtime.geometry.lattice.RectangularLattice;
import test.TestBase;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * An integration test of Rectangle and Rectangular Lattice.
 * It is easier and more informative to write this than to
 * write a mocked test.
 */
public class RectangleAndRectLatticeTest extends TestBase {

    private RectangularLattice lattice;
    private Rectangle query;

    private Coordinate2D inside, lowerLeft, upperRight;

    @Before
    public void init() throws Exception {
        lattice = new RectangularLattice();
        query = new Rectangle(lattice, 2, 3);
        lowerLeft = new Coordinate2D(-1, -1);
        upperRight = new Coordinate2D(2, 3);
        inside = new Coordinate2D(0, 0);
    }

    @Test
    public void getCanonicalSites() throws Exception {
        Set<Coordinate2D> expected = Stream.of(
                new Coordinate2D(0, 0),
                new Coordinate2D(0, 1),
                new Coordinate2D(0, 2),
                new Coordinate2D(1, 0),
                new Coordinate2D(1, 1),
                new Coordinate2D(1, 2)
        ).collect(Collectors.toSet());

        Set<Coordinate2D> actual = query
                .getCanonicalSites()
                .peek(c -> System.out.println(c))
                .collect(Collectors.toSet());

        assertSetsEqual(expected, actual);
    }

    @Test
    public void isOverboundsInside() throws Exception {
        assertFalse(query.isOverbounds(inside));
    }

    @Test
    public void isOverboundsLowerLeft() throws Exception {
        assertTrue(query.isOverbounds(lowerLeft));
    }

    @Test
    public void isOverboundsUpperRight() throws Exception {
        assertTrue(query.isOverbounds(upperRight));
    }

    @Test
    public void getOverboundsInside() throws Exception {
        Coordinate expected = new Coordinate2D(0, 0);
        Coordinate actual = query.getOverbounds(inside);
        assertEquals(expected, actual);
    }

    @Test
    public void getOverboundsLowerLeft() throws Exception {
        Coordinate expected = new Coordinate2D(-1, -1, true);
        Coordinate actual = query.getOverbounds(lowerLeft);
        assertEquals(expected, actual);
    }

    @Test
    public void getOverboundsUpperRight() throws Exception {
        Coordinate expected = new Coordinate2D(1, 1, true);
        Coordinate actual = query.getOverbounds(upperRight);
        assertEquals(expected, actual);
    }
}