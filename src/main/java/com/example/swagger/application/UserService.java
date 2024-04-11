package com.example.swagger.application;

import com.example.swagger.domain.dto.request.AddUserRequest;
import com.example.swagger.domain.entity.User;
import com.example.swagger.exception.UserNotFoundException;
import com.example.swagger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .name(dto.getName())
                .joinDate(dto.getJoinDate())
                .ssn(dto.getSsn())
                .build());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", userId)));
    }

    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", userId)));
        userRepository.delete(user);
    }
}
