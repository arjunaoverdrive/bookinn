package org.arjunaoverdrive.bookinn.web.payload.hotel;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelFilterRequest {
    @Positive
    private Long id;
    private String name;
    private String title;
    private String city;
    private String address;
    @Positive
    private Double downtownDistance;
    @Positive
    private Double rating;
    @Positive
    private Integer ratesCount;
}
