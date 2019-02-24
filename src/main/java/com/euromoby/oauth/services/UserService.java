package com.euromoby.oauth.services;

import com.euromoby.oauth.dtos.CreateUser;
import com.euromoby.oauth.entities.User;
import com.euromoby.oauth.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User create(CreateUser createUserDto) {
        User user = new User();

        user.setEmail(createUserDto.getEmail());
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());

        user.setPasswordHash(passwordEncoder.encode(createUserDto.getPassword()));

        return userRepository.save(user);
    }
}
