package org.arjunaoverdrive.bookinn.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.mappers.BookingMapper;
import org.arjunaoverdrive.bookinn.service.BookingService;
import org.arjunaoverdrive.bookinn.web.payload.booking.BookingListResponse;
import org.arjunaoverdrive.bookinn.web.payload.booking.BookingResponse;
import org.arjunaoverdrive.bookinn.web.payload.booking.UpsertBookingRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking")
public class BookingController {


    private final BookingMapper bookingMapper;

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody @Valid UpsertBookingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookingMapper.toResponse(bookingService.create(bookingMapper.toBooking(request))));
    }

    @GetMapping
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookingListResponse> findAllBookings(@ParameterObject
                                                               @PageableDefault(sort = "id",
                                                                       direction = Sort.Direction.ASC)
                                                               Pageable pageable) {
        return ResponseEntity.ok().body(bookingMapper.toListResponse(bookingService.findAll(pageable)));
    }
}
