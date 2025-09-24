package io.github.aglushkovsky.completable.future;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        CacheObjectService cacheObjectService = CacheObjectService.getInstance();

        CacheObject cacheObject = cacheObjectService.findById(1).join();

        System.out.println(cacheObject.toString());
    }
}
