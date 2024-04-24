package org.arjunaoverdrive.bookinn.domain.mappers;

import org.arjunaoverdrive.bookinn.domain.entities.Room;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomResponse;
import org.arjunaoverdrive.bookinn.web.payload.room.UpsertRoomRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    Room toRoom(UpsertRoomRequest request);

    default Room toRoom(Long id, UpsertRoomRequest request){
        Room room = toRoom(request);
        room.setId(id);
        return room;
    }

    RoomResponse toResponse(Room room);

}
