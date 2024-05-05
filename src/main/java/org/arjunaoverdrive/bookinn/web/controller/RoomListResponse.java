package org.arjunaoverdrive.bookinn.web.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomListResponse {

    private Integer totalRooms;
    private Integer totalPages;
    @Builder.Default
    private List<RoomResponse> rooms = new ArrayList<>();
}
