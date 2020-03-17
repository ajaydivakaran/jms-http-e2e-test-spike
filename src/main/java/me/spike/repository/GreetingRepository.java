package me.spike.repository;

import me.spike.contract.Greeting;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GreetingRepository {
    private static final List<Greeting> greetings = new ArrayList<Greeting>();

    public List<Greeting> getAll() {
        return greetings;
    }

    public void deleteAll() {
        greetings.clear();
    }

    public void save(Greeting greeting) {
        greetings.add(greeting);
    }
}
