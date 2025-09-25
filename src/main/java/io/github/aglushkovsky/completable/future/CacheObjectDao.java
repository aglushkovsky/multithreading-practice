package io.github.aglushkovsky.completable.future;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CacheObjectDao {

    public static final CacheObjectDao INSTANCE = new CacheObjectDao();

    private final Map<Integer, CacheObject> storage = new HashMap<>(
            Map.of(1, CacheObjectFactory.getMockCacheObject())
    );

    public Optional<CacheObject> findById(Integer id) {
        randomTimeSleep();
        return Optional.ofNullable(storage.get(id));
    }

    private static class CacheObjectFactory {

        private static final int ID_VALUE = 1;

        private static final String DUMMY_PROPERTY_VALUE = "dummy";

        private static final String STUPID_PROPERTY_VALUE = "stupid value";

        public static CacheObject getMockCacheObject() {
            return new CacheObject(ID_VALUE, DUMMY_PROPERTY_VALUE, STUPID_PROPERTY_VALUE);
        }
    }

    private static void randomTimeSleep() {
        ThreadLocalRandom.current().nextLong(3000, 5000);
    }

    public static CacheObjectDao getInstance() {
        return INSTANCE;
    }

    private CacheObjectDao() {
    }
}
