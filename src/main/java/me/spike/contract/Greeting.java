package me.spike.contract;

public class Greeting {
    private String message;

    public Greeting(String message) {
        this.message = message;
    }

    public Greeting() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
