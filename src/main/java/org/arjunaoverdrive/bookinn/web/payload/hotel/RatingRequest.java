package org.arjunaoverdrive.bookinn.web.payload.hotel;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingRequest {
    @Min(value = 1)
    @Max(value = 5)
    private Integer mark;
}
