package org.arjunaoverdrive.bookinn.kafka.message;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupMessage extends AbstractMessage {

    private Long userId;

    public SignupMessage() {
    }

    public SignupMessage(Long userId) {
        this.userId = userId;
    }

}
