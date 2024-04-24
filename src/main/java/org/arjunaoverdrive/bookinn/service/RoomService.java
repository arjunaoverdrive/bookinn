package org.arjunaoverdrive.bookinn.service;

import org.arjunaoverdrive.bookinn.domain.entities.Room;

public interface RoomService {

    Room findById(Long id);

    Room createRoom(Room room);

    Room updateRoom(Room room);

    void deleteById(Long id);
}
