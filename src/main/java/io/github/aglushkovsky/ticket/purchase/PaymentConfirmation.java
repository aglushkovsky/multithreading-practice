package io.github.aglushkovsky.ticket.purchase;

public class PaymentConfirmation {

    private final String id;

    public PaymentConfirmation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
