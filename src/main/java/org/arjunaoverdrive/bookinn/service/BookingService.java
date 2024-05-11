package org.arjunaoverdrive.bookinn.service;

import org.arjunaoverdrive.bookinn.domain.entities.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    Booking create(Booking booking);

    Page<Booking> findAll(Pageable pageable);

}
