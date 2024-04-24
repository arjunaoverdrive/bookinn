package org.arjunaoverdrive.bookinn.web.payload.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertRoomRequest {
    private String name;
    private String description;
    private String number;
    private BigDecimal price;
    private Integer capacity;
}
