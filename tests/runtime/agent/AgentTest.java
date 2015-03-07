/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent;

import org.junit.*;
import runtime.geometry.Coordinate;

import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AgentTest {

    private Agent query;
    private Supplier<Coordinate> locator;
    private Supplier<String> layerResolver;
    private int id = 5;

    @Before
    public void init() throws Exception {
        locator = mock(Supplier.class);
        layerResolver = mock(Supplier.class);
        id = 5;
        query = new Agent(id, layerResolver, locator);
    }

    @Test
    public void getId() throws Exception {
        assertEquals(5, query.getAgentId());
    }

    @Test
    public void getLayer() throws Exception {
        when(layerResolver.get()).thenReturn("layer");
        assertEquals("layer", query.getLayerId());
    }

    @Test
    public void locate() throws Exception {
        Coordinate expected = mock(Coordinate.class);
        when(locator.get()).thenReturn(expected);
        Coordinate actual = query.locate();
        assertSame(expected, actual);
    }
}