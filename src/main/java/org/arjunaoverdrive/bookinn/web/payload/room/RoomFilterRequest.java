package org.arjunaoverdrive.bookinn.web.payload.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomFilterRequest {
    private Long id;
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Integer capacity;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Long hotelId;
}
