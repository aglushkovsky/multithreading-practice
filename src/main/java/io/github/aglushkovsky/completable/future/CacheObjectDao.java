package io.github.aglushkovsky.completable.future;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class CacheObjectDao {

    public static final CacheObjectDao INSTANCE = new CacheObjectDao();

    private final Map<Integer, CacheObject> storage = new HashMap<>(
            Map.of(1, CacheObjectFactory.getMockCacheObject())
    );

    public Optional<CacheObject> findById(Integer id) {
        CacheObjectDaoUtil.randomTimeSleep();
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

    private static class CacheObjectDaoUtil {

        public static void randomTimeSleep() {
            try {
                Thread.sleep(new Random().nextLong(3000, 5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static CacheObjectDao getInstance() {
        return INSTANCE;
    }

    private CacheObjectDao() {
    }
}
