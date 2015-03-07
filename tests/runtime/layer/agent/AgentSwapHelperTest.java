/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import org.junit.*;
import org.mockito.InOrder;
import runtime.agent.Agent;
import runtime.geometry.Coordinate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AgentSwapHelperTest {

    private Coordinate p, q;
    private AgentLayerContent content;
    private AgentSwapHelper query;

    @Before
    public void init() throws Exception {
        p = mock(Coordinate.class);
        q = mock(Coordinate.class);

        content = mock(AgentLayerContent.class);
        query = new AgentSwapHelper(content);
    }

    @Test
    public void swapTwoOccupied() throws Exception {
        when(content.get(p)).thenReturn(1);
        when(content.get(q)).thenReturn(2);
        query.swap(p, q);
        InOrder inOrder = inOrder(content);
        inOrder.verify(content).remove(1);
        inOrder.verify(content).remove(2);
        inOrder.verify(content).put(1, q);
        inOrder.verify(content).put(2, p);
    }

    @Test
    public void swapOneVacant() throws Exception {
        when(content.get(p)).thenReturn(1);
        query.swap(p, q);
        InOrder inOrder = inOrder(content);
        inOrder.verify(content).remove(1);
        inOrder.verify(content).put(1, q);

        verify(content, never()).remove(null);
        verify(content, never()).put(2, q);
    }

    @Test
    public void swapBothVacant() throws Exception {
        query.swap(p, q);
        verify(content, never()).remove(any());
        verify(content, never()).put(any(), any());
    }
}