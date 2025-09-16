package io.github.aglushkovsky.forkjoindemo.factorial;

import java.util.concurrent.ForkJoinPool;

public class FactorialForkJoinDemo {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        Integer result = forkJoinPool.invoke(new FactorialTask(6)); // 6! = 720
        System.out.println(result);
    }
}
