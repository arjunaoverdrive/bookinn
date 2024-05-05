package org.arjunaoverdrive.bookinn.domain.mappers;

import org.arjunaoverdrive.bookinn.domain.entities.Room;
import org.arjunaoverdrive.bookinn.web.controller.RoomListResponse;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomResponse;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomSimpleResponse;
import org.arjunaoverdrive.bookinn.web.payload.room.UpsertRoomRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BookingMapper.class)
public interface RoomMapper {

    Room toRoom(UpsertRoomRequest request);

    default Room toRoom(Long id, UpsertRoomRequest request) {
        Room room = toRoom(request);
        room.setId(id);
        return room;
    }

    RoomResponse toResponse(Room room);

    RoomSimpleResponse toSimpleResponse(Room room);

    default RoomListResponse toListResponse(Page<Room> rooms) {
        RoomListResponse response = RoomListResponse.builder()
                .totalRooms((int) rooms.getTotalElements())
                .totalPages(rooms.getTotalPages())
                .rooms(rooms.stream()
                        .map(this::toResponse)
                        .toList())
                .build();
        return response;
    }
}
