package org.arjunaoverdrive.bookinn.domain.dao;

import org.arjunaoverdrive.bookinn.domain.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
