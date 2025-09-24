package io.github.aglushkovsky.completable.future;

import java.util.concurrent.CompletableFuture;

public class CacheObjectService {

    public static final CacheObjectService INSTANCE = new CacheObjectService();

    private final CacheObjectDao cacheObjectDao = CacheObjectDao.getInstance();

    private final CacheManager cacheManager = CacheManager.getInstance();

    public CompletableFuture<CacheObject> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> cacheManager.getCache(id).orElseThrow())
                .exceptionally(ex -> cacheObjectDao.findById(id).orElseThrow());
    }

    public static CacheObjectService getInstance() {
        return INSTANCE;
    }

    private CacheObjectService() {
    }
}
