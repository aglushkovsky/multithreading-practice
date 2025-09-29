package io.github.aglushkovsky.multithreading.practice;

public class Train {

    private final String id;

    private final Direction currentDirection;

    public Train(String id, Direction currentDirection) {
        this.id = id;
        this.currentDirection = currentDirection;
    }

    public String getId() {
        return id;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
