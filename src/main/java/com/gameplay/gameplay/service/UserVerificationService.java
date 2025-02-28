package com.gameplay.gameplay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserVerificationService {

    @Autowired
    private RestClient restClient;

    public boolean verifyUser(String userId) {
        String url = "http://localhost:8082/users/{userId}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-UserId", userId);

        ResponseEntity<Void> response = restClient.get()
                .uri(url)
                .header("X-UserId", userId)
                .retrieve()
                .toBodilessEntity();

        return response.getStatusCode().is2xxSuccessful();
    }
}
