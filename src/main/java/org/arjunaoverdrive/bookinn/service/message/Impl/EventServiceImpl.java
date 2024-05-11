package org.arjunaoverdrive.bookinn.service.message.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.bookinn.kafka.message.AbstractMessage;
import org.arjunaoverdrive.bookinn.kafka.message.BookingMessage;
import org.arjunaoverdrive.bookinn.kafka.message.SignupMessage;
import org.arjunaoverdrive.bookinn.service.message.EventService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final KafkaTemplate<String, AbstractMessage> kafkaMessageTemplate;

    @Value("${app.kafka.kafkaSignupTopic}")
    private String signupTopicName;

    @Value("${app.kafka.kafkaBookingTopic}")
    private String bookingTopicName;

    @Value("${app.kafka.kafkaMessageGroupId}")
    private String groupId;

    @Override
    public void publishSignupEvent(SignupMessage message) {
        CompletableFuture<SendResult<String, AbstractMessage>> future =
                kafkaMessageTemplate.send(signupTopicName, groupId, message);

        processFuture(message, future);
    }

    @Override
    public void publishBookingEvent(BookingMessage message) {
        CompletableFuture<SendResult<String, AbstractMessage>> future =
                kafkaMessageTemplate.send(bookingTopicName, groupId, message);

        processFuture(message, future);
    }

    private void processFuture(AbstractMessage message, CompletableFuture<SendResult<String, AbstractMessage>> future) {
        future.whenComplete((res, ex) -> {
            if(ex == null) {
                log.info("Sent message: {} with offset: {}", message, res.getRecordMetadata().offset());
            } else {
                log.warn("Unable to send message: {} due to {}", message, ex.getMessage());
            }
        });
    }
}
