package org.arjunaoverdrive.bookinn.domain.entities.mongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookings")
@Builder
public class BookingMongoEntity {
    @Id
    private BigInteger bookingId;
    private BigInteger userId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
}
