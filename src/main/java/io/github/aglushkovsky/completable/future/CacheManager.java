package io.github.aglushkovsky.completable.future;

import java.util.*;

public class CacheManager {

    private static final CacheManager INSTANCE = new CacheManager();

    private final Map<Integer, CacheObject> cache = new HashMap<>();

    public Optional<CacheObject> getCache(Integer id) {
        return Optional.ofNullable(cache.remove(id));
    }

    public void addCache(CacheObject cacheObject) {
        cache.put(cacheObject.getId(), cacheObject);
    }

    public static CacheManager getInstance() {
        return INSTANCE;
    }

    private CacheManager() {
    }
}
