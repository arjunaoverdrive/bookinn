package org.arjunaoverdrive.bookinn.service;

import org.arjunaoverdrive.bookinn.domain.entities.Hotel;
import org.arjunaoverdrive.bookinn.web.payload.hotel.RatingRequest;

public interface RatingService {
    void updateRating(Hotel fromDb, RatingRequest request);
}
