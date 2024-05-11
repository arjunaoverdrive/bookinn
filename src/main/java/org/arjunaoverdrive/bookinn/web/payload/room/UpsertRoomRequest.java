package org.arjunaoverdrive.bookinn.web.payload.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertRoomRequest {
    private String name;
    private String description;
    private String number;
    private Double price;
    private Integer capacity;
    private Long hotelId;
}
