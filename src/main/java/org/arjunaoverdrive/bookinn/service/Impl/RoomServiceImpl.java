package org.arjunaoverdrive.bookinn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.bookinn.domain.dao.RoomRepository;
import org.arjunaoverdrive.bookinn.domain.dao.RoomSpecification;
import org.arjunaoverdrive.bookinn.domain.entities.Room;
import org.arjunaoverdrive.bookinn.exception.CannotPersistEntityException;
import org.arjunaoverdrive.bookinn.exception.EntityNotFoundException;
import org.arjunaoverdrive.bookinn.service.RoomService;
import org.arjunaoverdrive.bookinn.util.BeanUtils;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Room with id {0} not found", id))
                );
    }

    @Override
    public Room createRoom(Room room) {
        return save(room);
    }

    @Override
    public Room updateRoom(Room room) {
        Room fromDb = findById(room.getId());
        BeanUtils.copyNonNullProperties(room, fromDb);
        return save(fromDb);
    }

    private Room save(Room room){
        try{
            room = roomRepository.save(room);
        }catch (Exception e){
            throw new CannotPersistEntityException(e.getMessage());
        }
        return room;
    }

    @Override
    public void deleteById(Long id) {
        Room fromDb = findById(id);
        log.info("Deleting room {}", fromDb);
        roomRepository.delete(fromDb);
    }

    @Override
    public Page<Room> findRooms(RoomFilterRequest filterRequest, Pageable pageable) {
        return roomRepository.findAll(RoomSpecification.withFilter(filterRequest), pageable);
    }
}
