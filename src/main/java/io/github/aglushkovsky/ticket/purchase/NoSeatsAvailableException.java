package io.github.aglushkovsky.ticket.purchase;

public class NoSeatsAvailableException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "No seats available for event with id=%s";

    private final String eventId;

    public NoSeatsAvailableException(String eventId) {
        super(MESSAGE_TEMPLATE.formatted(eventId));
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }
}
