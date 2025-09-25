package io.github.aglushkovsky.ticket.purchase;

public class Seat {

    private final String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
