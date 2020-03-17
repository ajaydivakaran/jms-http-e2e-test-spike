package me.spike.controller;

import me.spike.repository.GreetingRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private GreetingRepository repository;

    public GreetingController(GreetingRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/greetings", produces = MediaType.APPLICATION_JSON_VALUE)
    public GreetingsResponse getGreetings() {
        return new GreetingsResponse(repository.getAll());
    }
}
