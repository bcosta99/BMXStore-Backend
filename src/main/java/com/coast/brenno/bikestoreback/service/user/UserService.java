package com.coast.brenno.bikestoreback.service.user;


import com.coast.brenno.bikestoreback.model.User;
import com.coast.brenno.bikestoreback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User getUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No User found with username: " + username));
    }

}
