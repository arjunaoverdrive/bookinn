package org.arjunaoverdrive.bookinn.web.payload.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.arjunaoverdrive.bookinn.web.payload.hotel.HotelResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {

    private Long id;
    private String name;
    private String description;
    private String number;
    private BigDecimal price;
    private Integer capacity;
    @Builder.Default
    private Set<LocalDate> bookedDates = new HashSet<>();
    private HotelResponse hotel;
}
