package org.arjunaoverdrive.bookinn.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.mappers.UserMapper;
import org.arjunaoverdrive.bookinn.service.UserService;
import org.arjunaoverdrive.bookinn.web.payload.user.UpsertUserRequest;
import org.arjunaoverdrive.bookinn.web.payload.user.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long userId){
        return ResponseEntity.ok().body(userMapper.toResponse(userService.findById(userId)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UpsertUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toResponse(userService.createUser(userMapper.toUser(request))));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
                                                   @RequestBody @Valid UpsertUserRequest request){
        return ResponseEntity.ok().body(
                userMapper.toResponse(userService.updateUser(userMapper.toUser(userId, request))));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long userId){
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
