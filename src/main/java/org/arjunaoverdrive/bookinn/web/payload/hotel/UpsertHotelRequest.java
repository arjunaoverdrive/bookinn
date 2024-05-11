package org.arjunaoverdrive.bookinn.web.payload.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertHotelRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String title;
    @NotBlank
    private String address;
    @Positive
    private Double downtownDistance;
}
