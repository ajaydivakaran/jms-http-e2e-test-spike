package me.spike.controller;

import me.spike.contract.Greeting;
import me.spike.repository.GreetingRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetingController {

    private GreetingRepository repository;

    public GreetingController(GreetingRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/greetings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Greeting> getGreetings() {
        return repository.getAll();
    }
}
