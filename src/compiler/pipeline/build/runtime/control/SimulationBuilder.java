/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.build.runtime.control;

import compiler.pipeline.build.MapBuilder;
import runtime.control.Simulation;
import runtime.geometry.Geometry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dbborens on 3/13/15.
 */
public class SimulationBuilder extends MapBuilder<Simulation> {

    @Override
    protected Map<String, Class> resolveFields() {
        Map<String, Class> fields = new HashMap<>();
        fields.put("initially", List.class);
        fields.put("geometry", Geometry.class);
        return fields;
    }

    @Override
    protected Simulation instantiate() {
        return null;
    }
}
