package com.llp.amm.controller;


import com.llp.amm.dto.UserDto;
import com.llp.amm.entity.User;
import com.llp.amm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
