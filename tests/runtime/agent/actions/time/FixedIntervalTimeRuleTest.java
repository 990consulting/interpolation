/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions.time;

import org.junit.Before;
import org.junit.Test;
import test.TestBase;

import static org.junit.Assert.*;

public class FixedIntervalTimeRuleTest extends TestBase {

    private FixedIntervalTimeRule query;

    @Before
    public void init() throws Exception {
        query = new FixedIntervalTimeRule(0.0, 1.0, 1.0);
    }

    @Test
    public void hasNextYes() throws Exception {
        assertTrue(query.hasNext());
    }

    @Test
    public void hasNextNo() throws Exception {
        query.next();
        assertFalse(query.hasNext());
    }

    @Test
    public void next() throws Exception {
        assertEquals(0.0, query.next(), epsilon());
    }


    @Test(expected = IllegalArgumentException.class)
    public void badStartThrows() throws Exception {
        query = new FixedIntervalTimeRule(-1.0, 1.0, 2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badIntervalThrows() throws Exception {
        query = new FixedIntervalTimeRule(1.0, -1.0, 2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badEndThrows() throws Exception {
        query = new FixedIntervalTimeRule(2.0, 1.0, 1.0);
    }

    @Test(expected = IllegalStateException.class)
    public void nextPastEndThrows() throws Exception {
        query.next();
        query.next();
    }

}