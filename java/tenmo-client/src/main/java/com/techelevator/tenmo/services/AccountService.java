package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AccountService {
    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken;



    public AccountService(String url){
        baseUrl = url;
    }
    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }
    public Account getBalanceByUserName(String username){
        Account balance;
        ResponseEntity<Account> response = restTemplate.exchange(baseUrl + "balance/" + username,
                HttpMethod.GET, makeAuthEntity(), Account.class);
        balance = response.getBody();
        return balance;
    }
    public HttpEntity<Void>makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
