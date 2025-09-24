package io.github.aglushkovsky.completable.future;

public class CacheObject {

    private final Integer id;

    private final String dummyProperty;

    private final String stupidProperty;

    public CacheObject(Integer id, String dummyProperty, String stupidProperty) {
        this.id = id;
        this.dummyProperty = dummyProperty;
        this.stupidProperty = stupidProperty;
    }

    public Integer getId() {
        return id;
    }

    public String getDummyProperty() {
        return dummyProperty;
    }

    public String getStupidProperty() {
        return stupidProperty;
    }

    @Override
    public String toString() {
        return "CacheObject{" +
                "id=" + id +
                ", dummyProperty='" + dummyProperty + '\'' +
                ", stupidProperty='" + stupidProperty + '\'' +
                '}';
    }
}
