package org.arjunaoverdrive.bookinn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.bookinn.domain.dao.BookingRepository;
import org.arjunaoverdrive.bookinn.domain.entities.Booking;
import org.arjunaoverdrive.bookinn.domain.entities.Room;
import org.arjunaoverdrive.bookinn.exception.CannotPersistEntityException;
import org.arjunaoverdrive.bookinn.kafka.message.BookingMessage;
import org.arjunaoverdrive.bookinn.service.BookingService;
import org.arjunaoverdrive.bookinn.service.message.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final EventService eventService;

    @Override
    public Booking create(Booking booking) {
        Room room = booking.getRoom();
        room.addBooking(booking);
        Booking saved = save(booking);
        eventService.publishBookingEvent(new BookingMessage(saved.getId(), saved.getUser().getId(),
                saved.getCheckinDate(), saved.getCheckoutDate()));
        return saved;
    }

    private Booking save(Booking booking) {
        try {
            booking = repository.save(booking);
        } catch (Exception e) {
            throw new CannotPersistEntityException(e.getMessage());
        }
        return booking;
    }

    @Override
    public Page<Booking> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
