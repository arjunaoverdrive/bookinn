package org.arjunaoverdrive.bookinn.service.message.Impl;

import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.dao.SignupMessageRepository;
import org.arjunaoverdrive.bookinn.domain.entities.mongo.UserMongoEntity;
import org.arjunaoverdrive.bookinn.kafka.message.SignupMessage;
import org.arjunaoverdrive.bookinn.service.message.KafkaMessageService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class SignupMessageService implements KafkaMessageService<SignupMessage> {

    private final SignupMessageRepository signupMessageRepository;

    @Override
    public Class<SignupMessage> getType() {
        return SignupMessage.class;
    }

    @Override
    public String saveMessage(SignupMessage message) {
        UserMongoEntity entity = new UserMongoEntity();
        entity.setUserId(BigInteger.valueOf(message.getUserId()));
        return String.valueOf(signupMessageRepository.save(entity).getUserId());
    }
}
