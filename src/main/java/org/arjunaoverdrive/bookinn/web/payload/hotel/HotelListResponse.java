package org.arjunaoverdrive.bookinn.web.payload.hotel;

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
public class HotelListResponse {
    private Integer totalPages;
    private Integer totalHotels;
    @Builder.Default
    private List<HotelResponse> hotels = new ArrayList<>();
}
