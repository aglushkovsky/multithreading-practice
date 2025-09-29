package io.github.aglushkovsky.multithreading.practice;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class TunnelManager {

    private final Semaphore tunnel1 = new Semaphore(1);

    private final Semaphore tunnel2 = new Semaphore(1);

    private final AtomicReference<Direction> tunnel1Direction = new AtomicReference<>(Direction.NONE);

    private final AtomicReference<Direction> tunnel2Direction = new AtomicReference<>(Direction.NONE);

    private final long maxWaitTime;

    public TunnelManager(long maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public void passTunnel(Train train) throws InterruptedException {
        Direction trainDirection = train.getCurrentDirection();

        if (tryAcquireTunnel(tunnel1, tunnel1Direction, trainDirection)) {
            try {
                useTunnel(train, 1);
            } finally {
                releaseTunnel(tunnel1, tunnel1Direction);
            }
        } else if (tryAcquireTunnel(tunnel2, tunnel2Direction, trainDirection)) {
            try {
                useTunnel(train, 2);
            } finally {
                releaseTunnel(tunnel2, tunnel2Direction);
            }
        } else {
            tunnel1.acquire();
            try {
                tunnel1Direction.set(trainDirection);
                useTunnel(train, 1);
            } finally {
                releaseTunnel(tunnel1, tunnel1Direction);
            }
        }
    }

    private boolean tryAcquireTunnel(Semaphore tunnel, AtomicReference<Direction> tunnelDir,
                                     Direction trainDirection) throws InterruptedException {
        if (tunnel.tryAcquire(maxWaitTime, TimeUnit.MILLISECONDS)) {
            Direction currentDir = tunnelDir.get();
            if (currentDir == Direction.NONE || currentDir == trainDirection) {
                tunnelDir.set(trainDirection);
                return true;
            } else {
                tunnel.release();
                return false;
            }
        }
        return false;
    }

    private void releaseTunnel(Semaphore tunnel, AtomicReference<Direction> tunnelDir) {
        tunnelDir.set(Direction.NONE);
        tunnel.release();
    }

    private void useTunnel(Train train, int tunnelNumber) throws InterruptedException {
        System.out.println("Поезд " + train.getId() + " проходит через тоннель " +
                tunnelNumber + " в направлении " + train.getCurrentDirection());
        Thread.sleep(1000);
        System.out.println("Поезд " + train.getId() + " прошёл тоннель " + tunnelNumber);
    }
}
