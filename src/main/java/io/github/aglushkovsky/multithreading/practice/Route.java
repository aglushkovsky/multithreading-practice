package io.github.aglushkovsky.multithreading.practice;

import java.util.List;

public class Route {

    private final List<BusStop> busStops;

    public Route(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }
}
