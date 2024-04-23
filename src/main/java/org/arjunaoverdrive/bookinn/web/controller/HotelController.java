package org.arjunaoverdrive.bookinn.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.mappers.HotelMapper;
import org.arjunaoverdrive.bookinn.service.HotelService;
import org.arjunaoverdrive.bookinn.web.payload.hotel.HotelListResponse;
import org.arjunaoverdrive.bookinn.web.payload.hotel.HotelResponse;
import org.arjunaoverdrive.bookinn.web.payload.hotel.UpsertHotelRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
@Tag(name = "Hotel")
public class HotelController {
    private final HotelMapper hotelMapper;

    private final HotelService hotelService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> findById(@PathVariable Long hotelId){
        return ResponseEntity.ok().body(hotelMapper.toResponse(hotelService.findById(hotelId)));
    }

    @GetMapping
    public ResponseEntity<HotelListResponse> findHotels(@ParameterObject Pageable pageable){
        return ResponseEntity.ok().body(hotelMapper.toListResponse(hotelService.findHotelPage(pageable)));
    }

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody @Valid UpsertHotelRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelMapper.toResponse(hotelService.create(hotelMapper.toHotel(request))));
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable Long hotelId, @RequestBody @Valid UpsertHotelRequest request){
        return ResponseEntity.ok().body(hotelMapper.toResponse(hotelService.update(hotelMapper.toHotel(hotelId, request))));
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId){
        hotelService.deleteById(hotelId);
        return ResponseEntity.noContent().build();
    }

}
