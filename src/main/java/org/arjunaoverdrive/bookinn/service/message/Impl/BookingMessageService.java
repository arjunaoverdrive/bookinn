package org.arjunaoverdrive.bookinn.service.message.Impl;

import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.dao.BookingMessageRepository;
import org.arjunaoverdrive.bookinn.domain.entities.mongo.BookingMongoEntity;
import org.arjunaoverdrive.bookinn.kafka.message.BookingMessage;
import org.arjunaoverdrive.bookinn.service.message.KafkaMessageService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class BookingMessageService implements KafkaMessageService<BookingMessage> {

    private final BookingMessageRepository bookingMessageRepository;
    @Override
    public Class<BookingMessage> getType() {
        return BookingMessage.class;
    }

    @Override
    public String saveMessage(BookingMessage message) {
        BookingMongoEntity entity = BookingMongoEntity.builder()
                .bookingId(BigInteger.valueOf(message.getId()))
                .checkinDate(message.getCheckinDate())
                .checkoutDate(message.getCheckoutDate())
                .userId(BigInteger.valueOf(message.getUserId()))
                .build();

        return String.valueOf(bookingMessageRepository.save(entity).getBookingId());
    }


}
