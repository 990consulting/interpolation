/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.util;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a collection, chooses a random member of the collection.
 *
 * Created by dbborens on 3/5/15.
 */
public class RandomChooser<T> implements Function<Stream<T>, T> {

    private Random random;

    public RandomChooser (Random random) {
        this.random = random;
    }

    @Override
    public T apply(Stream<T> stream) {
        List<T> ts = stream.collect(Collectors.toList());
        int n = ts.size();
        int i = random.nextInt(n);
        return ts.get(i);
    }
}
