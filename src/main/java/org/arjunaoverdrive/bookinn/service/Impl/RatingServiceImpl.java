package org.arjunaoverdrive.bookinn.service.Impl;

import org.arjunaoverdrive.bookinn.domain.entities.Hotel;
import org.arjunaoverdrive.bookinn.service.RatingService;
import org.arjunaoverdrive.bookinn.web.payload.hotel.RatingRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class RatingServiceImpl implements RatingService {
    @Override
    public void updateRating(Hotel fromDb, RatingRequest request) {
        Double rating = fromDb.getRating();
        int ratesCount = fromDb.getRatesCount();

        Integer newMark = request.getMark();
        rating = ratesCount == 0 ? newMark : computeRating(newMark, rating, ratesCount);

        fromDb.setRating(rating);
        fromDb.setRatesCount(ratesCount + 1);
    }

    private Double computeRating(Integer newMark, Double rating, int ratesCount) {
        double totalRating = rating * ratesCount;

        totalRating = totalRating - rating + newMark;

        rating = (double) Math.round(totalRating / ratesCount * 10) / 10;
        return rating;
    }
}
