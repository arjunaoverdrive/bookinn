package org.arjunaoverdrive.bookinn.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.bookinn.kafka.message.AbstractMessage;
import org.arjunaoverdrive.bookinn.service.message.KafkaMessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageListener<T extends AbstractMessage> {

    private final List<KafkaMessageService<T>> services;

    @KafkaListener(topics = {"${app.kafka.kafkaSignupTopic}", "${app.kafka.kafkaBookingTopic}"},
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(
            @Payload T message,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = true) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);

        Class<? extends AbstractMessage> aClass = message.getClass();
        KafkaMessageService kafkaMessageService = services.stream()
                .filter(s -> s.getType().equals(aClass))
                .findFirst().orElseThrow(() -> new RuntimeException(
                        MessageFormat.format("Service with the generic type: {0} not found!", aClass)));

        String id = kafkaMessageService.saveMessage(message);
        log.info("Saved message {} to database", id);
    }
}
