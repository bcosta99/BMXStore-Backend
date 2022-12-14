package com.coast.brenno.bikestoreback.service.greeting;

import com.coast.brenno.bikestoreback.repository.GreetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingService {
    private final GreetingRepository repository;

    public String getGreeting() {
        return "Greeting from service";
    }

}