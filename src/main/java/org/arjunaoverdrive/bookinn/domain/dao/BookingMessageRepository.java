package org.arjunaoverdrive.bookinn.domain.dao;

import org.arjunaoverdrive.bookinn.domain.entities.mongo.BookingMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingMessageRepository extends MongoRepository<BookingMongoEntity, String>{

    @Query("{ 'checkinDate': { $gt: ?0, $lt: ?1 }, 'checkoutDate': { $gt: ?0, $lt: ?1 } }")
    List<BookingMongoEntity> findByCheckinDateBetweenAndCheckoutDateBetween(LocalDate from, LocalDate to);
}