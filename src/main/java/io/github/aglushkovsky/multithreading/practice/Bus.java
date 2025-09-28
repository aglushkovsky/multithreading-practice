package io.github.aglushkovsky.multithreading.practice;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;

public class Bus {

    private final String id;

    private final Route route;

    public Bus(String id, Route route) {
        this.id = id;
        this.route = route;
    }

    public void startRoute() {
        Iterator<BusStop> iterator = route.getIteratorStartingFromBusStopWithSpecifiedBus(this);
        while (iterator.hasNext()) {
            BusStop busStop = iterator.next();
            BusStop nextBusStop = busStop.getNextBusStop().orElseThrow();
            Condition nextBusStopCondition = nextBusStop.getCondition();

            nextBusStop.getLock().lock();
            try {
                if (!nextBusStop.isAvailableToArrive()) {
                    System.out.printf("Автобус с id=%s ждёт свободного места на остановке с id=%s%n", getId(), nextBusStop.getId());
                    nextBusStopCondition.await();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                nextBusStop.getLock().unlock();
            }

            busStop.getLock().lock();
            try {
                busStop.removeBus(this);
                System.out.printf("Автобус с id=%s убыл с остановки с id=%s%n", getId(), busStop.getId());
                busStop.getCondition().signalAll();
                System.out.printf("Автобус с id=%s сообщил ждущим автобусам о своём убытии с остановки с id=%s%n", getId(), busStop.getId());
            } finally {
                busStop.getLock().unlock();
            }

            try {
                System.out.printf("Автобус с id=%s в пути на остановку с id=%s%n", getId(), nextBusStop.getId());
                Thread.sleep(500 + ThreadLocalRandom.current().nextInt(500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

            nextBusStop.getLock().lock();
            try {
                nextBusStop.addBus(this);
                System.out.printf("Автобус с id=%s прибыл на остановку с id=%s%n", getId(), nextBusStop.getId());
            } finally {
                nextBusStop.getLock().unlock();
            }
        }
    }

    public String getId() {
        return id;
    }
}
