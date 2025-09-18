package io.github.aglushkovsky.threadpooldemo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static io.github.aglushkovsky.threadpooldemo.FileUtil.*;

public class CopyPaster {

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    private final Queue<FilePair> storage = new ArrayDeque<>();

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public CopyPaster() {
        executorService.execute(getWatcherTask());
    }

    private Runnable getWatcherTask() {
        return () -> {
            while (true) {
                FilePair filePair = null;
                lock.lock();
                try {
                    if (!storage.isEmpty()) {
                        filePair = storage.remove();
                    } else {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }

                if (filePair == null) {
                    continue;
                }

                try {
                    Path path = filePair.path();
                    Path outputPath = renameFile(path);
                    Files.write(outputPath, filePair.lines());
                    System.out.printf("Из хранилища взят файл %s и записан в %s%n", path, outputPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public void addFileToRead(String filename) {
        executorService.execute(() -> {
            try {
                Path path = Paths.get(filename);
                lock.lock();
                try {
                    storage.add(new FilePair(path, Files.readAllLines(path)));
                    System.out.println("Прочитан файл: " + filename);
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public record FilePair(Path path, List<String> lines) {
    }
}
