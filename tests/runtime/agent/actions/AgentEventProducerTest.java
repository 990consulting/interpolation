/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.Agent;
import runtime.agent.actions.time.FixedIntervalTimeRule;
import runtime.schedule.event.DeterministicEvent;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class AgentEventProducerTest {

    private Function<Agent, Action> actionProducer;
    private Supplier<FixedIntervalTimeRule> timeRuleSupplier;
    private FixedIntervalTimeRule rule;
    private Agent agent;
    private AgentEventProducer query;
    private Action action;
    private DeterministicEvent event;

    @Before
    public void init() throws Exception {
        actionProducer = mock(Function.class);
        timeRuleSupplier = mock(Supplier.class);

        rule = mock(FixedIntervalTimeRule.class);
        when(rule.hasNext()).thenReturn(true);
        when(timeRuleSupplier.get()).thenReturn(rule);

        action = mock(Action.class);
        agent = mock(Agent.class);
        when(actionProducer.apply(agent)).thenReturn(action);

        query = new AgentEventProducer(actionProducer, timeRuleSupplier);
        event = query.apply(agent);
    }

    @Test
    public void productHasParameterAgent() throws Exception {
        event.run();
        assertSame(agent, event.getAgent());
    }

    @Test
    public void applyAsksActionProducer() throws Exception {
        event.run();
        verify(action).run();
    }

    @Test
    public void applyAsksTimeRuleSupplier() throws Exception {
        verify(rule).next();
    }
}