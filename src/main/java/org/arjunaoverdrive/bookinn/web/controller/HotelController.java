package org.arjunaoverdrive.bookinn.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.mappers.HotelMapper;
import org.arjunaoverdrive.bookinn.service.HotelService;
import org.arjunaoverdrive.bookinn.web.payload.hotel.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
@Tag(name = "Hotel")
public class HotelController {
    private final HotelMapper hotelMapper;

    private final HotelService hotelService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> findById(@PathVariable Long hotelId) {
        return ResponseEntity.ok().body(hotelMapper.toResponse(hotelService.findById(hotelId)));
    }

    @GetMapping
    public ResponseEntity<HotelListResponse> findHotels(
            @ParameterObject @Valid HotelFilterRequest filter,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
                                                        Pageable pageable) {
        return ResponseEntity.ok().body(hotelMapper.toListResponse(hotelService.findHotelPage(filter, pageable)));
    }

    @PostMapping
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<HotelResponse> createHotel(@RequestBody @Valid UpsertHotelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelMapper.toResponse(hotelService.create(hotelMapper.toHotel(request))));
    }

    @PutMapping("/{hotelId}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable Long hotelId,
                                                     @RequestBody @Valid UpsertHotelRequest request) {
        return ResponseEntity.ok()
                .body(hotelMapper.toResponse(hotelService.update(hotelMapper.toHotel(hotelId, request))));
    }

    @DeleteMapping("/{hotelId}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId) {
        hotelService.deleteById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{hotelId}/rate")
    public ResponseEntity<HotelResponse> rateHotel(@PathVariable Long hotelId,
                                                    @RequestBody @Valid RatingRequest request){
        return ResponseEntity.ok().body(hotelMapper.toResponse(hotelService.rateHotel(hotelId, request)));
    }

}
