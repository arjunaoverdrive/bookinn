package org.arjunaoverdrive.bookinn.domain.entities.mongo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserMongoEntity {
    @Id
    private BigInteger userId;

    @DocumentReference(collection = "bookings", lookup = "{'user':?#{#self._id} }")
    private List<BookingMongoEntity> bookings = new ArrayList<>();
}
