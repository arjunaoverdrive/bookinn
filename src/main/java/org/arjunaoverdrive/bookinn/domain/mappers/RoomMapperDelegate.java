package org.arjunaoverdrive.bookinn.domain.mappers;

import org.arjunaoverdrive.bookinn.domain.entities.Hotel;
import org.arjunaoverdrive.bookinn.domain.entities.Room;
import org.arjunaoverdrive.bookinn.service.HotelService;
import org.arjunaoverdrive.bookinn.web.payload.room.UpsertRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RoomMapperDelegate implements RoomMapper{

    @Autowired
    private HotelService hotelService;

    @Override
    public Room toRoom(UpsertRoomRequest request) {
        Hotel hotel = hotelService.findById(request.getHotelId());

        Room room = new Room();
        room.setHotel(hotel);
        room.setDescription(request.getDescription());
        room.setNumber(request.getNumber());
        room.setCapacity(request.getCapacity());
        room.setPrice(request.getPrice());
        room.setName(request.getName());

        return room;
    }
}
