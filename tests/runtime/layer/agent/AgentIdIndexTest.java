/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.Agent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AgentIdIndexTest {


    private AgentIdIndex query;

    @Before
    public void init() throws Exception {
        query = new AgentIdIndex();
    }

    @Test
    public void nextIdAdvances() throws Exception {
        assertEquals(0, query.nextId());
        assertEquals(1, query.nextId());
    }

    @Test
    public void addResolve() throws Exception {
        Agent agent = mockAgent(3);
        query.add(agent);
        Agent resolved = query.resolve(3);
        assertSame(resolved, agent);
    }


    @Test(expected = IllegalStateException.class)
    public void addExistingIdThrows() throws Exception {
        query.add(mockAgent(3));
        query.add(mockAgent(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void resolveUnrecognizedIdThrows() throws Exception {
        query.resolve(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addRemoveResolve() throws Exception {
        Agent agent = mockAgent(3);
        query.add(agent);
        query.remove(agent);
        query.resolve(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeUnrecognizedThrows() throws Exception {
        Agent agent = mockAgent(3);
        query.remove(agent);
    }

    @Test(expected = IllegalStateException.class)
    public void contentMismatchThrows() throws Exception {
        Agent correct = mockAgent(3);
        query.add(correct);

        Agent incorrect = mockAgent(3);
        query.remove(incorrect);
    }

    private Agent mockAgent(int id) {
        Agent agent = mock(Agent.class);
        when(agent.getAgentId()).thenReturn(id);
        return agent;
    }
}