package org.arjunaoverdrive.bookinn.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.mappers.BookingMapper;
import org.arjunaoverdrive.bookinn.service.BookingService;
import org.arjunaoverdrive.bookinn.web.payload.booking.BookingListResponse;
import org.arjunaoverdrive.bookinn.web.payload.booking.BookingResponse;
import org.arjunaoverdrive.bookinn.web.payload.booking.UpsertBookingRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {


    private final BookingMapper bookingMapper;

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody @Valid UpsertBookingRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookingMapper.toResponse(bookingService.create(bookingMapper.toBooking(request))));
    }

    @GetMapping
    public ResponseEntity<BookingListResponse> findAllBookings(@ParameterObject Pageable pageable){
        return ResponseEntity.ok().body(bookingMapper.toListResponse(bookingService.findAll(pageable)));
    }
}
