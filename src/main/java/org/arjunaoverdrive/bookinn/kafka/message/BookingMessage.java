package org.arjunaoverdrive.bookinn.kafka.message;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class BookingMessage extends AbstractMessage{

    private Long id;
    private Long userId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;

    public BookingMessage() {
    }

    public BookingMessage(Long id, Long userId, LocalDate checkinDate, LocalDate checkoutDate) {
        this.id = id;
        this.userId = userId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

}
