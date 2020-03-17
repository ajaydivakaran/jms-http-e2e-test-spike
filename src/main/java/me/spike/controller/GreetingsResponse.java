package me.spike.controller;

import me.spike.contract.Greeting;

import java.util.List;

public class GreetingsResponse {
    private List<Greeting> greetings;

    public GreetingsResponse() {
    }

    public GreetingsResponse(List<Greeting> greetings) {
        this.greetings = greetings;
    }

    public List<Greeting> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<Greeting> greetings) {
        this.greetings = greetings;
    }

    public boolean hasGreetings() {
        return greetings != null && greetings.size() > 0;
    }
}
