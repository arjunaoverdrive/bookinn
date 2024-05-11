package org.arjunaoverdrive.bookinn.service.message;

public interface KafkaMessageService<T> {
    Class<T> getType();

    String saveMessage(T message);
}
