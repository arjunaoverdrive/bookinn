package org.arjunaoverdrive.bookinn.service.dto;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StatisticsDto {
    private BigInteger userId;
    private BigInteger bookingId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
}
