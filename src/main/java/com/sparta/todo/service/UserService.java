package com.sparta.todo.service;

import com.sparta.todo.Entity.User;
import com.sparta.todo.dto.UserRequestDto;
import com.sparta.todo.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void join(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()){
            throw new DuplicateRequestException();
        }

        User user = new User(username, password);
        userRepository.save(user);
    }
}
