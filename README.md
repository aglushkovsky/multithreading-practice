реализовать процесс имитации покупки билета с использованием `CompletableFuture`, в котором несколько этапов(проверка наличия билетов, поиск места и обработка оплаты), при этом результаты одного этапа передаются в следующий.

Создать класс `TicketServiceImitation` симуляции покупки билетов со следующими сигнатурами методов

`public CompletableFuture<Boolean> checkAvailability(String eventId)` - проверка наличия билетов
`public CompletableFuture<Seat> reserveSeat(String eventId)` - резервирование места, если оно доступно
`public CompletableFuture<PaymentConfirmation> processPayment(Seat seat)` - обработка платежа

Использовать классы:

```java
class Seat {
    private String seatNumber;
    public Seat(String seatNumber) { this.seatNumber = seatNumber; }
    public String getSeatNumber() { return seatNumber; }
}
```

```java
class PaymentConfirmation {
    private String id;
    public PaymentConfirmation(String id) { this.id = id; }
    public String getId() { return id; }
}
```

написать логику методов класса `TicketServiceImitation`. Cоздать класс `TicketServiceDemo`, в main методе которого продемонстрировать цепочку вызовов методов `TicketServiceImitation`, также предусмотреть обработку ошибок если они есть