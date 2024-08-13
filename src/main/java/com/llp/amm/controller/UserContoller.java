package com.llp.amm.controller;


import com.llp.amm.dtos.UserDto;
import com.llp.amm.entity.User;
import com.llp.amm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserContoller {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto) {

        User savedUser = userService.addUser(userDto);

        return ResponseEntity.ok(savedUser);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();// Retorna HTTP 204 No Content em caso de sucesso
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        UserDto userDto = UserDto.fromEntity(user);

        return ResponseEntity.ok(userDto);
    }
        @PutMapping("/{id}")
        public ResponseEntity<UserDto>updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        User updateUser = userService.updateUser(id,userDto);
        UserDto updatedUserDto = UserDto.fromEntity(updateUser);
        return ResponseEntity.ok(updatedUserDto);

    }
}
