/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent;

import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AgentDeathSupplierTest {

    private Consumer<Agent> layerConsumer, scheduleConsumer;
    private AgentDeathSupplier query;
    private Agent agent;
    private Runnable runnable;

    @Before
    public void init() throws Exception {
        layerConsumer = mock(Consumer.class);
        scheduleConsumer = mock(Consumer.class);
        agent = mock(Agent.class);
        query = new AgentDeathSupplier(layerConsumer, scheduleConsumer);
        runnable = query.apply(agent);
        runnable.run();
    }

    @Test
    public void deathNotifiesLayer() throws Exception {
        verify(layerConsumer).accept(agent);
    }

    @Test
    public void deathNotifiesScheduler() throws Exception {
        verify(scheduleConsumer).accept(agent);
    }
}