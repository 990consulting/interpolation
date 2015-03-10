package test;/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by dbborens on 2/15/15.
 */
public abstract class TestBase {
    private final static double epsilon = calcEpsilon();

    private static double calcEpsilon() {
        double eps = 1.0D;

        while ((1.0 + (eps / 2.0)) != 1.0) {
            eps /= 2.0;
        }

        return eps;
    }

    protected double epsilon() {
        return epsilon;
    }

    protected boolean epsilonEquals(double p, double q) {
        double delta = p - q;
        double magnitude = Math.abs(delta);
        return (magnitude < epsilon());
    }

    protected <T> List<T> listOf(T... contents) {
        return Stream
                .of(contents)
                .collect(Collectors.toList());
    }

    protected <T> void assertStreamsEqual(Stream<T> expected, Stream<T> actual) {
        List<T> expList = expected.collect(Collectors.toList());
        List<T> actList = actual.collect(Collectors.toList());
        assertEquals(expList, actList);
    }
}
