package io.github.aglushkovsky.forkjoindemo.lowercase;

import java.util.concurrent.RecursiveTask;

public class LowercaseTask extends RecursiveTask<String> {

    private static final int MIN_SUBSTRING_LENGTH = 5;

    private final String value;

    public LowercaseTask(String value) {
        this.value = value;
    }

    @Override
    protected String compute() {
        if (value.length() <= MIN_SUBSTRING_LENGTH) {
            return value.toLowerCase();
        }

        int mid = value.length() / 2;

        LowercaseTask lowercaseTask1 = new LowercaseTask(value.substring(0, mid));
        LowercaseTask lowercaseTask2 = new LowercaseTask(value.substring(mid));
        lowercaseTask1.fork();
        return lowercaseTask1.join() + lowercaseTask2.compute();
    }
}
