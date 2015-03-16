/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.control;

import compiler.pipeline.build.runtime.control.SimulationBuilder;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.ClassToListAdapter;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.runtime.process.ProcessClassSymbolTable;
import runtime.control.Simulation;
import runtime.process.agent.AgentProcess;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/13/15.
 */
public class SimulationSymbolTable extends MapSymbolTable<Simulation> {

    private SimulationBuilder builder;

    public SimulationSymbolTable() {
        builder = new SimulationBuilder();
    }

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> members = new HashMap<>();
        MemberSymbol initially = initially();
        members.put("initially", initially);
        return members;
    }

    @Override
    public SimulationBuilder getBuilder() {
        return new SimulationBuilder();
    }

    @Override
    protected HashMap<String, Function<Simulation, ?>> resolveReserved() {
        HashMap<String, Function<Simulation, ?>> reserved = new HashMap<>();
        Function<Simulation, Double> time = simulation -> simulation.getTime();
        reserved.put("time", time);

        return reserved;
    }

    /* Symbols */

    private MemberSymbol initially() {
        ClassSymbolTable processSymbolTable = new ProcessClassSymbolTable();
        Supplier<ListSymbolTable<AgentProcess>> initiallySupplier = () -> new ListSymbolTable<>(processSymbolTable);
        ClassToListAdapter<AgentProcess> initiallyAdapter = new ClassToListAdapter<>(initiallySupplier);

        return new MemberSymbol(initiallyAdapter, "A list of zero or more " +
                "processes to be executed prior to the start of the " +
                "simulation.");
    }

}

