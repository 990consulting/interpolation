/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.agent.Agent;
import runtime.geometry.Geometry;
import runtime.geometry.coordinate.Coordinate;

/**
 * Created by dbborens on 3/5/15.
 */
public class AgentLayer {

    private final AgentLayerContent content;
    private final AgentSwapHelper swapHelper;
    private final Geometry geometry;

    public AgentLayer(AgentLayerContent content,
                      Geometry geometry,
                      AgentSwapHelper swapHelper) {

        this.content = content;
        this.geometry = geometry;
        this.swapHelper = swapHelper;
    }

    public AgentLayer(AgentLayerContent content, Geometry geometry) {
        this(content, geometry, new AgentSwapHelper(content));
    }

    public Coordinate locate(Agent agent) {
        return content.locate(agent);
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void swap(Coordinate p, Coordinate q) {
        swapHelper.swap(p, q);
    }
}
