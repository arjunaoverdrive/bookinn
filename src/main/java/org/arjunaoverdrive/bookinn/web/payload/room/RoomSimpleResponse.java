package org.arjunaoverdrive.bookinn.web.payload.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.arjunaoverdrive.bookinn.web.payload.hotel.HotelResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomSimpleResponse {

    private Long id;
    private String name;
    private String description;
    private String number;
    private Double price;
    private Integer capacity;
    private HotelResponse hotel;
}
