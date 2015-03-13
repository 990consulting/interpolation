/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.control;

import org.junit.Before;
import org.junit.Test;
import runtime.schedule.EventSchedule;
import runtime.util.halt.HaltCondition;

import static org.mockito.Mockito.*;

public class SimulationTest {

    private EventSchedule schedule;
    private Simulation query;

    @Before
    public void init() throws Exception {
        schedule = mock(EventSchedule.class);
        doThrow(mock(HaltCondition.class)).when(schedule).advance();
        query = new Simulation(schedule);
    }

    @Test
    public void runStopsWithHalt() throws Exception {
        query.run();
        verify(schedule, times(1)).advance();
    }
}