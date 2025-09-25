package io.github.aglushkovsky.ticket.purchase;

public class TicketServiceDemo {

    public static void main(String[] args) {
        TicketServiceImitation ticketServiceImitation = new TicketServiceImitation();
        String targetEventId = "1";

        PaymentConfirmation paymentConfirmation = ticketServiceImitation.checkAvailability(targetEventId)
                .thenCompose(isAvailable -> ticketServiceImitation.reserveSeat(targetEventId))
                .thenCompose(ticketServiceImitation::processPayment)
                .join();

        System.out.printf("Был куплен билет на ивент %s на место %s%n", targetEventId, paymentConfirmation.getId());
    }
}
