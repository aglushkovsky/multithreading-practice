package io.github.aglushkovsky.multithreading.practice;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

    public static void main(String[] args) {

        BusStop firstBusStop = new BusStop("1bs");
        BusStop secondBusStop = new BusStop("2bs");
        BusStop thirdBusStop = new BusStop("3bs");

        firstBusStop.setNextBusStop(secondBusStop);
        secondBusStop.setNextBusStop(thirdBusStop);

        Route route = new Route(List.of(firstBusStop, secondBusStop, thirdBusStop));

        Bus firstBus = new Bus("1a", route);
        Bus secondBus = new Bus("2a", route);
        Bus thirdBus = new Bus("3a", route);
        Bus fourthBus = new Bus("4a", route);

        secondBusStop.addBus(secondBus);
        secondBusStop.addBus(thirdBus);
        secondBusStop.addBus(fourthBus);

        firstBusStop.addBus(firstBus);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(firstBus::startRoute);
        executorService.execute(secondBus::startRoute);
        executorService.execute(thirdBus::startRoute);
        executorService.execute(fourthBus::startRoute);

        executorService.shutdown();
    }
}
