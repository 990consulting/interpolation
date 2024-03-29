/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions.helpers;

import org.junit.Test;
import runtime.geometry.coordinate.Coordinate;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SelfAndOtherConsumerTest {

    @Test
    public void testAccept() throws Exception {
        Coordinate input = mock(Coordinate.class);
        Coordinate myLocation = mock(Coordinate.class);
        Supplier<Coordinate> locator = () -> myLocation;
        BiConsumer<Coordinate, Coordinate> consumer = mock(BiConsumer.class);
        SelfAndOtherConsumer query = new SelfAndOtherConsumer(locator, consumer);
        query.accept(input);
        verify(consumer).accept(myLocation, input);
    }
}