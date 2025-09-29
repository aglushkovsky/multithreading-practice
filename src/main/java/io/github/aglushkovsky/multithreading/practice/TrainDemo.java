package io.github.aglushkovsky.multithreading.practice;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrainDemo {

    public static void main(String[] args) throws InterruptedException {
        Train firstTrain = new Train("1t", Direction.SOUTH_NORTH);
        Train secondTrain = new Train("2t", Direction.NORTH_SOUTH);
        Train thirdTrain = new Train("3t", Direction.NORTH_SOUTH);

        TunnelManager tunnelManager = new TunnelManager(500);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (Train train : List.of(firstTrain, secondTrain, thirdTrain)) {
            executorService.submit(() -> {
                try {
                    tunnelManager.passTunnel(train);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }
}
