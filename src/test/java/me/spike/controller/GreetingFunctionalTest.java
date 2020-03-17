package me.spike.controller;

import me.spike.contract.Greeting;
import me.spike.repository.GreetingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class GreetingFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private GreetingRepository greetingRepository;

    @Before
    public void setUp() {
        greetingRepository.deleteAll();
    }

    @Test
    public void shouldReturnGreetings() {
        jmsTemplate.convertAndSend("greeting", "{\"message\":\"hello world\"}");

        await().atMost(2, SECONDS).until(() -> {
            final GreetingsResponse actualResponse = restTemplate.getForObject("/greetings", GreetingsResponse.class);
            return actualResponse != null && actualResponse.hasGreetings();
        });

        final GreetingsResponse actualResponse = restTemplate.getForObject("/greetings", GreetingsResponse.class);
        assertNotNull(actualResponse);
        assertEquals(1, actualResponse.getGreetings().size());
        assertEquals("hello world", actualResponse.getGreetings().get(0).getMessage());
    }
}
