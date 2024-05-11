package org.arjunaoverdrive.bookinn.service.message;

import org.arjunaoverdrive.bookinn.kafka.message.BookingMessage;
import org.arjunaoverdrive.bookinn.kafka.message.SignupMessage;

public interface EventService {

    void publishSignupEvent(SignupMessage message);
    void publishBookingEvent(BookingMessage message);
}
