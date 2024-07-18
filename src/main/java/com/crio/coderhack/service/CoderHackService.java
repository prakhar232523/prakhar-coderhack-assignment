package com.crio.coderhack.service;

import com.crio.coderhack.dto.UserRegistrationRequestDto;
import com.crio.coderhack.model.User;

import java.util.List;

public interface CoderHackService {
    User registerUser(UserRegistrationRequestDto userRequest);
    User updateUserScore(String userId, Integer score);
    List<User> getAllUsers();
    User getUserById(String userId);
    void deleteUser(String userId);

}
