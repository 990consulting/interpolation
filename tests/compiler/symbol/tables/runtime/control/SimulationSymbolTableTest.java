/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.control;

import compiler.pipeline.build.MapBuilder;
import compiler.pipeline.build.runtime.control.SimulationBuilder;
import compiler.symbol.tables.runtime.process.ScatterSymbolTable;
import org.junit.Before;
import org.junit.Test;
import runtime.control.Simulation;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimulationSymbolTableTest
        extends AbstractMapSymbolTableTest<SimulationSymbolTable> {

    @Before
    public void init() throws Exception {
        query = new SimulationSymbolTable();
    }

    @Test
    public void getBuilder() throws Exception {
        MapBuilder builder = query.getBuilder();
        assertTrue(builder instanceof SimulationBuilder);
    }

    @Test
    public void getSymbolTableForInitially() throws Exception {
        doListSymbolTableTest("initially", "scatter", ScatterSymbolTable.class);
    }

    @Test
    public void getFunctionForTime() throws Exception {
        Simulation simulation = mock(Simulation.class);
        when(simulation.getTime()).thenReturn(5.0);
        Function<Simulation, Double> timeFunction = query.getReservedFunction("time");
        Double actual = timeFunction.apply(simulation);
        assertEquals(5.0, actual, epsilon());
    }

    @Test
    public void verifyReservedKeywords() throws Exception {
        Stream<String> expected = Stream.of("time");
        Stream<String> actual = query.getReservedKeywords();
        assertStreamsEqual(expected, actual);
    }
}