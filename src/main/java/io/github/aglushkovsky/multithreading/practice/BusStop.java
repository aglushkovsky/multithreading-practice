package io.github.aglushkovsky.multithreading.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusStop {

    private static final int BUS_STOP_CAPACITY = 3;

    private final List<Bus> buses = new ArrayList<>();

    private final Lock lock = new ReentrantLock();

    private final String id;

    private BusStop nextBusStop;

    private final Condition condition = lock.newCondition();

    public BusStop(String id) {
        this.id = id;
    }

    public Optional<BusStop> getNextBusStop() {
        return Optional.ofNullable(nextBusStop);
    }

    public boolean addBus(Bus bus) {
        if (buses.size() > BUS_STOP_CAPACITY) {
            return false;
        }
        buses.add(bus);
        return true;
    }

    public boolean removeBus(Bus bus) {
        return buses.remove(bus);
    }

    public boolean isAvailableToArrive() {
        return buses.size() < BUS_STOP_CAPACITY;
    }

    public boolean contains(Bus bus) {
        return buses.contains(bus);
    }

    public Lock getLock() {
        return lock;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getId() {
        return id;
    }

    public void setNextBusStop(BusStop nextBusStop) {
        this.nextBusStop = nextBusStop;
    }
}
