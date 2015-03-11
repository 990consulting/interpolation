/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.Agent;
import runtime.geometry.Geometry;
import runtime.geometry.coordinate.Coordinate;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class AgentLayerTest {

    private AgentLayerContent content;
    private Geometry geometry;
    private AgentSwapHelper swapHelper;
    private AgentLayer query;
    private Agent agent;
    @Before
    public void init() throws Exception {
        content = mock(AgentLayerContent.class);
        geometry = mock(Geometry.class);
        swapHelper = mock(AgentSwapHelper.class);
        agent = mock(Agent.class);
        query = new AgentLayer(content, geometry, swapHelper);
    }

    @Test
    public void locateAsksContent() throws Exception {
        Coordinate expected = mock(Coordinate.class);
        when(content.locate(agent)).thenReturn(expected);
        Coordinate actual = query.locate(agent);
        assertSame(expected, actual);
    }

    @Test
    public void getGeometry() throws Exception {
        Geometry actual = query.getGeometry();
        assertSame(geometry, actual);
    }

    @Test
    public void swapAsksHelper() throws Exception {
        Coordinate p = mock(Coordinate.class);
        Coordinate q = mock(Coordinate.class);

        query.swap(p, q);
        verify(swapHelper).swap(p, q);
    }
}