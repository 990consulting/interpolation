/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build.builders.runtime.control;

import compiler.pipeline.build.builders.Builder;
import compiler.pipeline.translate.nodes.ObjectNode;
import runtime.control.Simulation;

/**
 * Created by dbborens on 3/13/15.
 */
public class SimulationBuilder implements Builder<Simulation> {

    @Override
    public Simulation instantiate(ObjectNode node) {
        return null;
    }

}
