package io.github.aglushkovsky.multithreading.practice;

import java.util.Iterator;
import java.util.List;

public class Route {

    private final List<BusStop> busStops;

    public Route(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }

    public Iterator<BusStop> getIteratorStartingFromBusStopWithSpecifiedBus(Bus bus) {
        BusStop startBusStop = busStops.stream()
                .filter(busStop -> busStop.contains(bus))
                .findFirst()
                .orElseThrow();
        return new RouteIterator(startBusStop);
    }

    private static class RouteIterator implements Iterator<BusStop> {

        private BusStop currentBusStop;

        public RouteIterator(BusStop busStop) {
            currentBusStop = busStop;
        }

        @Override
        public boolean hasNext() {
            return currentBusStop.getNextBusStop().isPresent();
        }

        @Override
        public BusStop next() {
            var result = currentBusStop;
            this.currentBusStop = currentBusStop.getNextBusStop().get();
            return result;
        }
    }
}
