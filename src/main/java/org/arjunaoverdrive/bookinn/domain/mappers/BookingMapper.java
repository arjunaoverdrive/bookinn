package org.arjunaoverdrive.bookinn.domain.mappers;

import org.arjunaoverdrive.bookinn.domain.entities.Booking;
import org.arjunaoverdrive.bookinn.web.payload.booking.BookingListResponse;
import org.arjunaoverdrive.bookinn.web.payload.booking.BookingResponse;
import org.arjunaoverdrive.bookinn.web.payload.booking.BookingSimpleResponse;
import org.arjunaoverdrive.bookinn.web.payload.booking.UpsertBookingRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    Booking toBooking(UpsertBookingRequest request);

    BookingResponse toResponse(Booking booking);

    BookingSimpleResponse toSimpleResponse(Booking booking);

    default BookingListResponse toListResponse(Page<Booking> bookingPage) {

        return BookingListResponse.builder()
                .totalPages(bookingPage.getTotalPages())
                .bookings(bookingPage.getContent().stream()
                        .map(this::toResponse).toList())
                .build();
    }
}
