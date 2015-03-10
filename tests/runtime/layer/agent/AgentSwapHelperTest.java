/*
 * Copyright (c) b0a5 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import runtime.agent.Agent;
import runtime.geometry.Coordinate;

import static org.mockito.Mockito.*;

public class AgentSwapHelperTest {

    private Coordinate p, q;
    private Agent a, b;

    private AgentLayerContent content;
    private AgentSwapHelper query;

    @Before
    public void init() throws Exception {
        p = mock(Coordinate.class);
        q = mock(Coordinate.class);

        a = mock(Agent.class);
        b = mock(Agent.class);

        content = mock(AgentLayerContent.class);
        query = new AgentSwapHelper(content);
    }

    @Test
    public void swapTwoOccupied() throws Exception {
        when(content.get(p)).thenReturn(a);
        when(content.get(q)).thenReturn(b);
        query.swap(p, q);
        InOrder inOrder = inOrder(content);
        inOrder.verify(content).remove(a);
        inOrder.verify(content).remove(b);
        inOrder.verify(content).put(a, q);
        inOrder.verify(content).put(b, p);
    }

    @Test
    public void swapOneVacant() throws Exception {
        when(content.get(p)).thenReturn(a);
        query.swap(p, q);
        InOrder inOrder = inOrder(content);
        inOrder.verify(content).remove(a);
        inOrder.verify(content).put(a, q);

        verify(content, never()).remove(null);
        verify(content, never()).put(b, q);
    }

    @Test
    public void swapBothVacant() throws Exception {
        query.swap(p, q);
        verify(content, never()).remove(any());
        verify(content, never()).put(any(), any());
    }
}