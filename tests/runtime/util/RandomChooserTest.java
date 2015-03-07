/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.util;

import org.junit.*;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RandomChooserTest {

    private Random random;
    private RandomChooser<Integer> query;

    @Before
    public void init() throws Exception {
        random = mock(Random.class);
        query = new RandomChooser(random);
    }

    @Test
    public void apply() throws Exception {
        Stream<Integer> stream = IntStream
                .range(0, 5)
                .boxed();
        when(random.nextInt(5)).thenReturn(3);
        Integer actual = query.apply(stream);
        assertEquals(new Integer(3), actual);
    }
}