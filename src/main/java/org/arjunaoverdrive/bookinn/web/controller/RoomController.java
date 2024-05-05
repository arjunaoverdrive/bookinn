package org.arjunaoverdrive.bookinn.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.mappers.RoomMapper;
import org.arjunaoverdrive.bookinn.service.RoomService;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomFilterRequest;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomResponse;
import org.arjunaoverdrive.bookinn.web.payload.room.UpsertRoomRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@Tag(name = "Room")
public class RoomController {

    private final RoomMapper roomMapper;
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<RoomListResponse> filter(
            @ParameterObject @Valid RoomFilterRequest filterRequest,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok().body(roomMapper.toListResponse(roomService.findRooms(filterRequest, pageable)));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long roomId) {
        return ResponseEntity.ok().body(roomMapper.toResponse(roomService.findById(roomId)));
    }

    @PostMapping
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoomResponse> createRoom(@RequestBody @Valid UpsertRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(roomMapper.toResponse(roomService.createRoom(roomMapper.toRoom(request))));
    }

    @PutMapping("/{roomId}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long roomId,
                                                   @RequestBody @Valid UpsertRoomRequest request) {
        return ResponseEntity.ok()
                .body(roomMapper.toResponse(roomService.updateRoom(roomMapper.toRoom(roomId, request))));

    }

    @DeleteMapping("/{roomId}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long roomId) {
        roomService.deleteById(roomId);
        return ResponseEntity.noContent().build();
    }
}
