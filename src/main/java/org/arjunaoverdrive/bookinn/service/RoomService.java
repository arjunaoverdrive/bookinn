package org.arjunaoverdrive.bookinn.service;

import org.arjunaoverdrive.bookinn.domain.entities.Room;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    Room findById(Long id);

    Room createRoom(Room room);

    Room updateRoom(Room room);

    void deleteById(Long id);

    Page<Room> findRooms(RoomFilterRequest filterRequest, Pageable pageable);
}
