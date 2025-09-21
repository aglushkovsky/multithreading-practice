package io.github.aglushkovsky.threadpooldemo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

import static io.github.aglushkovsky.threadpooldemo.FileUtil.*;

public class CopyPaster {

    private final ExecutorService readersExecutorService = Executors.newFixedThreadPool(3);

    private final ExecutorService watcherExecutorService = Executors.newSingleThreadExecutor();

    private final BlockingQueue<FilePair> storage = new LinkedBlockingQueue<>();

    public CopyPaster() {
        watcherExecutorService.execute(getWatcherTask());
    }

    private Runnable getWatcherTask() {
        return () -> {
            while (true) {
                try {
                    FilePair filePair = storage.take();

                    Path path = filePair.path();
                    Path outputPath = renameFile(path);
                    Files.write(outputPath, filePair.lines());
                    System.out.printf("Из хранилища взят файл %s и записан в %s%n", path, outputPath);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public void addFileToRead(String filename) {
        readersExecutorService.execute(() -> {
            try {
                Path path = Paths.get(filename);
                storage.add(new FilePair(path, Files.readAllLines(path)));
                System.out.println("Прочитан файл: " + filename);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public record FilePair(Path path, List<String> lines) {
    }
}
