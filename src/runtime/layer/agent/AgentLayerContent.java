/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.geometry.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/6/15.
 */
public class AgentLayerContent {

    private final Map<Coordinate, Integer> coordToAgentMap;
    private final Map<Integer, Coordinate> agentToCoordMap;

    public AgentLayerContent(Stream<Coordinate> canonicalSites) {
        coordToAgentMap = new HashMap<>();
        canonicalSites.forEach(c -> coordToAgentMap.put(c, null));

        agentToCoordMap = new HashMap<>();
    }

    public void put(Integer id, Coordinate coordinate) {
        if (!coordToAgentMap.containsKey(coordinate)) {
            throw new IllegalArgumentException("Attempting placement to non-canonical coordinate");
        }

        if (agentToCoordMap.containsKey(id)) {
            throw new IllegalArgumentException("Attempting double placement of agent");
        }

        if (coordToAgentMap.get(coordinate) != null) {
            throw new IllegalArgumentException("Attempting double coordinate occupancy");
        }

        coordToAgentMap.put(coordinate, id);
        agentToCoordMap.put(id, coordinate);
    }

    public Integer get(Coordinate coordinate) {
        if (!coordToAgentMap.containsKey(coordinate)) {
            throw new IllegalArgumentException("Attempting to access contents of non-canonical coordinate");
        }
        return coordToAgentMap.get(coordinate);
    }

    public void remove(Integer id) {
        if (!agentToCoordMap.containsKey(id)) {
            throw new IllegalArgumentException("Attempting to remove agent that does not exist on this layer");
        }

        Coordinate c = agentToCoordMap.get(id);
        coordToAgentMap.put(c, null);
        agentToCoordMap.remove(id);
    }

    public Coordinate locate(Integer agentId) {
        return agentToCoordMap.get(agentId);
    }
}
