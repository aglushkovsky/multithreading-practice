package io.github.aglushkovsky.forkjoindemo.factorial;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {

    private final int value;

    public FactorialTask(int value) {
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (value <= 1) {
            return 1;
        }

        FactorialTask factorialTask = new FactorialTask(value - 1);
        factorialTask.fork();
        return factorialTask.join() * value;
    }
}
