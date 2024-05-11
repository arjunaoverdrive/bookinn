package org.arjunaoverdrive.bookinn.web.payload.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingListResponse {
    private Integer totalPages;

    @Builder.Default
    private List<BookingResponse> bookings = new ArrayList<>();
}
