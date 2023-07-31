package com.example.socksStorage.controller;

import com.example.socksStorage.dto.SocksDTO;
import com.example.socksStorage.entity.Socks;
import com.example.socksStorage.repository.SocksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SocksControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private SocksRepository socksRepository;


    @Test
    public void shouldThrowNotFoundSocksException() {
        String url = "http://localhost:" + port + "/api/socks/add";
        String request = """
                {
                  "partNumber": 215,
                  "color": "RED",
                  "size": "SIZE_36",
                  "cottonPart": 0,
                  "quantity": 0
                }""";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    public void shouldReleaseSocks() {
        String url = "http://localhost:" + port + "/api/socks/add";
        String request = """
                {
                  "partNumber": null,
                  "color": "RED",
                  "size": "SIZE_36",
                  "cottonPart": 0,
                  "quantity": 10
                }""";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(
                response.getBody(),
                "{\"partNumber\":1,\"color\":\"RED\",\"size\":\"SIZE_36\",\"cottonPart\":0,\"quantity\":10}");
        System.out.println(response.getBody());

        String request2 = """
                {
                  "partNumber": 1,
                  "color": "RED",
                  "size": "SIZE_36",
                  "cottonPart": 0,
                  "quantity": 10
                }""";
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity2 = new HttpEntity<>(request2, headers2);
        ResponseEntity<String> response2 = restTemplate.postForEntity(url, requestEntity2, String.class);
        Assertions.assertEquals(HttpStatus.OK, response2.getStatusCode());
        System.out.println(response2.getBody());

        String urlRelease = "http://localhost:" + port + "/api/socks/release";
        String request3 = """
                {
                  "partNumber": 1,
                  "color": "RED",
                  "size": "SIZE_36",
                  "cottonPart": 0,
                  "quantity": 10
                }""";
        HttpHeaders headers3 = new HttpHeaders();
        headers3.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity3 = new HttpEntity<>(request3, headers3);
        ResponseEntity<SocksDTO> response3 = restTemplate.postForEntity(urlRelease, requestEntity3, SocksDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response3.getStatusCode());
        assertEquals(10, response3.getBody().getQuantity());

        Socks socksInDB = socksRepository.findById(1L).get();
        assertNotNull(socksInDB);
        System.out.println(socksInDB);
    }

}