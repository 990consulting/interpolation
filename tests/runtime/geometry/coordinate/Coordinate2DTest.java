/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.geometry.coordinate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Coordinate2DTest {

    private final Coordinate2D c = new Coordinate2D(1, 2);

    @Test
    public void x() throws Exception {
        assertEquals(1, c.x());
    }

    @Test
    public void y() throws Exception {
        assertEquals(2, c.y());
    }

    @Test
    public void isOverbounds() throws Exception {
        assertFalse(c.isOverbounds());
    }

    @Test
    public void asOverbounds() throws Exception {
        Coordinate expected = new Coordinate2D(1, 2, true);
        assertEquals(expected, c.asOverbounds());
    }

    @Test
    public void complexConstructor() throws Exception {
        Coordinate cEquiv = new Coordinate2D(1, 2, false);
        assertEquals(cEquiv, c);
    }
}