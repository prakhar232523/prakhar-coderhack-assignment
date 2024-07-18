package com.crio.coderhack.controller;

import com.crio.coderhack.dto.ScoreUpdateDto;
import com.crio.coderhack.dto.UserRegistrationRequestDto;
import com.crio.coderhack.model.User;
import com.crio.coderhack.service.CoderHackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class CoderHackController {

    @Autowired
    CoderHackService coderHackService;

    @PostMapping()
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationRequestDto userRequest) {
        User registeredUser = coderHackService.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId , @Valid @RequestBody ScoreUpdateDto scoreUpdateDto ) {
        User updatedUser = coderHackService.updateUserScore(userId, scoreUpdateDto.getScore());
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = coderHackService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@Valid @PathVariable String userId) {
        User user = coderHackService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@Valid @PathVariable String userId) {
        coderHackService.deleteUser(userId);
    }

}
