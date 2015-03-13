/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.util;

import org.junit.Before;
import org.junit.Test;
import test.TestBase;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RandomChooserTest extends TestBase {

    private Random random;
    private RandomChooser<Integer> query;
    private Stream<Integer> domain;
    @Before
    public void init() throws Exception {
        random = mock(Random.class);
        query = new RandomChooser(random);
        domain = IntStream
                .range(0, 5)
                .boxed();
    }

    @Test
    public void apply() throws Exception {
        when(random.nextInt(5)).thenReturn(3);
        Integer actual = query.apply(domain);
        assertEquals(new Integer(3), actual);
    }

    @Test
    public void choose() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(1);
        Stream<Integer> expected = IntStream.of(0, 2, 3).boxed();
        Stream<Integer> actual = query.choose(domain, 3);
        assertStreamsEqual(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void chooseNegativeCountThrows() throws Exception {
        query.choose(domain, -1);
    }

    @Test
    public void chooseMoreThanMaxReturnsAll() throws Exception {
        Set<Integer> expected = domain.collect(Collectors.toSet());
        Set<Integer> actual = query.choose(expected.stream(), 6)
                .collect(Collectors.toSet());

        assertSetsEqual(expected, actual);
    }

}