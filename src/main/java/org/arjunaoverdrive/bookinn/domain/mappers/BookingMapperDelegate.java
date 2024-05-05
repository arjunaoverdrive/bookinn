package org.arjunaoverdrive.bookinn.domain.mappers;

import org.arjunaoverdrive.bookinn.domain.entities.Booking;
import org.arjunaoverdrive.bookinn.service.RoomService;
import org.arjunaoverdrive.bookinn.service.UserService;
import org.arjunaoverdrive.bookinn.web.payload.booking.UpsertBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BookingMapperDelegate implements BookingMapper{

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Override
    public Booking toBooking(UpsertBookingRequest request) {
        Booking booking = new Booking();

        booking.setCheckinDate(request.getCheckinDate());
        booking.setCheckoutDate(request.getCheckoutDate());
        booking.setRoom(roomService.findById(request.getRoomId()));
        booking.setUser(userService.findById(request.getUserId()));

        return booking;
    }
}
