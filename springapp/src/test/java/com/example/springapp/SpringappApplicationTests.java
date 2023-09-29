
package com.example.springapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringappApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHelloWorldWithValidUser() {
        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("root", "examly").getForEntity("/hello", String.class);
        assertEquals("Hello, World!", responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue()); // Should return 200 OK
    }

    @Test
    public void testHelloWorldWithInvalidUser() {
        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("invalidUser", "invalidPassword").getForEntity("/hello", String.class);
        assertEquals(401, responseEntity.getStatusCodeValue()); // Should return 401 Unauthorized
    }

    @Test
    public void testHelloWorldWithoutAuthentication() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/hello", String.class);
        assertEquals(401, responseEntity.getStatusCodeValue()); // Should return 401 Unauthorized
    }
}
