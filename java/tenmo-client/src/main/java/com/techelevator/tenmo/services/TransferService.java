package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Target;
import java.math.BigDecimal;

public class TransferService {
    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken;

    public TransferService(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }
    public Transfer transferFrom(int userIdFrom){
        Transfer transfer;
        ResponseEntity<Transfer> response = restTemplate.exchange(baseUrl + "transferto/" + userIdFrom,
                HttpMethod.GET, makeAuthEntity(), Transfer.class);
        transfer = response.getBody();
        return transfer;

    }

    public HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

}
