package org.arjunaoverdrive.bookinn.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.mappers.RoomMapper;
import org.arjunaoverdrive.bookinn.service.RoomService;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomResponse;
import org.arjunaoverdrive.bookinn.web.payload.room.UpsertRoomRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomMapper roomMapper;
    private final RoomService roomService;

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long roomId){
        return ResponseEntity.ok().body(roomMapper.toResponse(roomService.findById(roomId)));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody @Valid UpsertRoomRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(roomMapper.toResponse(roomService.createRoom(roomMapper.toRoom(request))));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long roomId,
                                                   @RequestBody @Valid UpsertRoomRequest request){
        return ResponseEntity.ok()
                .body(roomMapper.toResponse(roomService.updateRoom(roomMapper.toRoom(roomId, request))));

    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long roomId){
        roomService.deleteById(roomId);
        return ResponseEntity.noContent().build();
    }
}
