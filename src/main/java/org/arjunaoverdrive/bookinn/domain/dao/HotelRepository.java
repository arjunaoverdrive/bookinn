package org.arjunaoverdrive.bookinn.domain.dao;

import org.arjunaoverdrive.bookinn.domain.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
