/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import runtime.agent.Agent;
import runtime.agent.AgentDeathSupplier;
import runtime.agent.AgentEstablisher;
import runtime.geometry.coordinate.Coordinate;
import runtime.layer.agent.AgentIdIndex;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class AgentEstablisherTest {

    private AgentIdIndex idIndex;
    private Function<Agent, Coordinate> agentLocator;
    private AgentEventEstablisher eventEstablisher;
    private BiConsumer<Agent, Coordinate> agentLayerPlacer;
    private AgentDeathSupplier deathSupplier;
    private AgentEstablisher query;
    private Coordinate c;

    @Captor
    private ArgumentCaptor<Agent> captor;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        idIndex = mock(AgentIdIndex.class);
        agentLocator = mock(Function.class);
        eventEstablisher = mock(AgentEventEstablisher.class);
        agentLayerPlacer = mock(BiConsumer.class);
        deathSupplier = mock(AgentDeathSupplier.class);
        c = mock(Coordinate.class);
        when(idIndex.nextId()).thenReturn(5);

        query = new AgentEstablisher(idIndex,
                agentLocator,
                eventEstablisher,
                agentLayerPlacer,
                deathSupplier);
    }

    @Test
    public void establishPlacesAgent() throws Exception {
        query.establish(c);
//        ArgumentCaptor<Agent> captor = ArgumentCaptor.forClass(Agent.class);
        verify(agentLayerPlacer).accept(captor.capture(), eq(c));
        assertEquals(5, captor.getValue().getAgentId());
    }

    @Test
    public void establishSchedulesEvents() throws Exception {
        query.establish(c);
        verify(eventEstablisher).accept(captor.capture());
        assertEquals(5, captor.getValue().getAgentId());
    }

    @Test
    public void establishInitializesAgent() throws Exception {
        Coordinate d = mock(Coordinate.class);
        when(agentLocator.apply(any())).thenReturn(d);

        Runnable runnable = mock(Runnable.class);
        when(deathSupplier.apply(any())).thenReturn(runnable);

        query.establish(c);
        verify(agentLayerPlacer).accept(captor.capture(), eq(c));
        Agent agent = captor.getValue();

        assertSame(d, agent.locate());

        agent.die();
        verify(runnable).run();
    }

}