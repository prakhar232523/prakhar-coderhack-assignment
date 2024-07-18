package com.crio.coderhack.service;

import com.crio.coderhack.dto.UserRegistrationRequestDto;
import com.crio.coderhack.exception.UserAlreadyExistsException;
import com.crio.coderhack.exception.UserNotFoundException;
import com.crio.coderhack.model.Badge;
import com.crio.coderhack.model.User;
import com.crio.coderhack.repository.CoderHackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoderHackServiceImpl implements CoderHackService {

    @Autowired
    CoderHackRepository coderHackRepository;

    public User registerUser(UserRegistrationRequestDto userRequest) {
        if(coderHackRepository.existsById(userRequest.getUserId())) {
            throw new UserAlreadyExistsException("User with user Id " + userRequest.getUserId() +" already exists!");
        }
        User user = new User(userRequest.getUserId(), userRequest.getUserName());
        return coderHackRepository.save(user);
    }

    public User updateUserScore(String userId, Integer score) {
        User user = coderHackRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with user ID " + userId +  " does not exist in database!"));

        user.setScore(score);
        updateBadges(user, score);
        return coderHackRepository.save(user);
    }

    private void updateBadges(User user, int score) {
        Set<Badge> badges = user.getBadges();

        if (score >= 1 && score < 30) { badges.add(Badge.CODE_CHAMP); }
        else if (score >= 30 && score < 60) { badges.add(Badge.CODE_NINJA); }
        else badges.add(Badge.CODE_MASTER);
    }

    public List<User> getAllUsers() {
        return coderHackRepository.findAll().stream()
                .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
                .collect(Collectors.toList());
    }

    public User getUserById(String userId) {
        return coderHackRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist"));
    }

    public void deleteUser(String userId) {
        if(!coderHackRepository.existsById(userId)) { throw new UserNotFoundException("No such user found in database"); }
        coderHackRepository.deleteById(userId);
    }

}
