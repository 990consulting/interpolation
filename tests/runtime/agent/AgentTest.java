/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent;

import org.junit.Before;
import org.junit.Test;
import runtime.geometry.coordinate.Coordinate;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class AgentTest {

    private Agent query;
    private Supplier<Coordinate> locator;
//    private Supplier<String> layerResolver;
    private Runnable death;
    private int id = 5;

    @Before
    public void init() throws Exception {
        locator = mock(Supplier.class);
//        layerResolver = mock(Supplier.class);
        death = mock(Runnable.class);
        id = 5;
//        query = new Agent(id, layerResolver, locator, death);
        query = new Agent(id);
        query.init(locator, death);
    }

    @Test
    public void getId() throws Exception {
        assertEquals(5, query.getAgentId());
    }

//    @Test
//    public void getLayer() throws Exception {
//        when(layerResolver.get()).thenReturn("layer");
//        assertEquals("layer", query.getLayerId());
//    }

    @Test
    public void locate() throws Exception {
        Coordinate expected = mock(Coordinate.class);
        when(locator.get()).thenReturn(expected);
        Coordinate actual = query.locate();
        assertSame(expected, actual);
    }

    @Test
    public void die() throws Exception {
        query.die();
        verify(death).run();
    }
}