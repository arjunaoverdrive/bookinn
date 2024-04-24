package org.arjunaoverdrive.bookinn.web.payload.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomResponse;
import org.arjunaoverdrive.bookinn.web.payload.user.UserResponse;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private Long id;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private UserResponse userResponse;
    private RoomResponse roomResponse;
}
