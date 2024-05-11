package org.arjunaoverdrive.bookinn.web.payload.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.arjunaoverdrive.bookinn.web.payload.booking.BookingSimpleResponse;
import org.arjunaoverdrive.bookinn.web.payload.hotel.HotelResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {

    private Long id;
    private String name;
    private String description;
    private String number;
    private Double price;
    private Integer capacity;
    private HotelResponse hotel;
    @Builder.Default
    private List<BookingSimpleResponse> bookings = new ArrayList<>();
}
