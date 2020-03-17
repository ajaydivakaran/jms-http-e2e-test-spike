package me.spike.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.spike.contract.Greeting;
import me.spike.repository.GreetingRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GreetingReceiver {

    private GreetingRepository repository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public GreetingReceiver(GreetingRepository repository) {
        this.repository = repository;
    }

    @JmsListener(destination = "greeting")
    public void receive(String greeting) throws IOException {
        repository.save(objectMapper.readValue(greeting, Greeting.class));
    }

}
