package org.arjunaoverdrive.bookinn.service.Impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.bookinn.domain.entities.Hotel;
import org.arjunaoverdrive.bookinn.domain.dao.HotelRepository;
import org.arjunaoverdrive.bookinn.exception.CannotPersistEntityException;
import org.arjunaoverdrive.bookinn.exception.EntityNotFoundException;
import org.arjunaoverdrive.bookinn.service.HotelService;
import org.arjunaoverdrive.bookinn.service.RatingService;
import org.arjunaoverdrive.bookinn.util.BeanUtils;
import org.arjunaoverdrive.bookinn.web.payload.hotel.RatingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final RatingService ratingService;

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                MessageFormat.format("Hotel with id {0} not found", id)
                        )
                );
    }

    @Override
    public Page<Hotel> findHotelPage(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    @Override
    public Hotel create(Hotel hotel) {
        return save(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        Hotel fromDb = findById(hotel.getId());
        BeanUtils.copyNonNullProperties(hotel, fromDb);
        return save(hotel);
    }

    private Hotel save(Hotel hotel) {
        try {
            hotel = hotelRepository.save(hotel);
        } catch (Exception e) {
            throw new CannotPersistEntityException(e.getMessage());
        }
        return hotel;
    }

    @Override
    public void deleteById(Long id) {
        Hotel fromDb = findById(id);
        hotelRepository.delete(fromDb);
        log.info("Deleted hotel {}", fromDb);
    }

    @Override
    public Hotel rateHotel(Long hotelId, RatingRequest request) {
        Hotel fromDb = findById(hotelId);
        ratingService.updateRating(fromDb, request);
        return save(fromDb);
    }
}
