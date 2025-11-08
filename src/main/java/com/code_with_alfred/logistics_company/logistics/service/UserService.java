package com.code_with_alfred.logistics_company.logistics.service;


import com.code_with_alfred.logistics_company.logistics.entity.Role;
import com.code_with_alfred.logistics_company.logistics.entity.User;
import com.code_with_alfred.logistics_company.logistics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public List<User> getActiveDrivers() {
        return userRepository.findAllActiveDrivers();
    }
}