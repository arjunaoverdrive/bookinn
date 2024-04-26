package org.arjunaoverdrive.bookinn.web.payload.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelResponse {
    private Long id;
    private String name;
    private String city;
    private String address;
    private Double downtownDistance;
    @Builder.Default
    private Double rating = 1.;
    @Builder.Default
    private Integer ratesCount = 0;
}
