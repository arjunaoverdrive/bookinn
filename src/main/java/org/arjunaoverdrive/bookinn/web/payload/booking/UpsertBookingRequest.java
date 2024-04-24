package org.arjunaoverdrive.bookinn.web.payload.booking;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.arjunaoverdrive.bookinn.validation.annotations.DatesAvailable;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DatesAvailable
public class UpsertBookingRequest {

    @Future
    @NotBlank
    private LocalDate checkinDate;
    @NotBlank
    @Future
    private LocalDate checkoutDate;
    @Positive
    @NotNull
    private Long roomId;
    @Positive
    @NotNull
    private Long userId;
}
