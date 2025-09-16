package io.github.aglushkovsky.forkjoindemo.lowercase;

import java.util.concurrent.ForkJoinPool;

public class LowercaseForkJoinDemo {

    public static void main(String[] args) {
        String str = "JdssJs^321JlsaksadklJSjjsajsSJKJLskSk*(*dsaFGh";
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        String result = forkJoinPool.invoke(new LowercaseTask(str));
        System.out.println(result);
    }
}
