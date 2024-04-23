package org.arjunaoverdrive.bookinn.service;

import org.arjunaoverdrive.bookinn.domain.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {

    Hotel findById(Long id);
    Page<Hotel> findHotelPage(Pageable pageable);

    Hotel create(Hotel hotel);

    Hotel update(Hotel hotel);

    void deleteById(Long id);
}
