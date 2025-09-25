package io.github.aglushkovsky.ticket.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class TicketServiceImitation {

    private static final String MOCK_SEAT_NUMBER = "1";

    private final Map<String, List<Seat>> seatAvailabilityMap = Map.of(
            MOCK_SEAT_NUMBER,
            new ArrayList<>(
                    List.of(
                            new Seat("1"),
                            new Seat("2"),
                            new Seat("3")
                    )
            )
    );

    public CompletableFuture<Boolean> checkAvailability(String eventId) {
        return CompletableFuture.supplyAsync(() -> seatAvailabilityMap.containsKey(eventId));
    }

    public CompletableFuture<Seat> reserveSeat(String eventId) {
        return CompletableFuture.supplyAsync(() -> {
            List<Seat> seats = Optional.ofNullable(seatAvailabilityMap.get(eventId)).orElseThrow();
            if (seats.isEmpty()) {
                throw new NoSeatsAvailableException(eventId);
            }
            return seats.remove(0);
        });
    }

    public CompletableFuture<PaymentConfirmation> processPayment(Seat seat) {
        return CompletableFuture.supplyAsync(() -> new PaymentConfirmation(seat.getSeatNumber()));
    }
}
