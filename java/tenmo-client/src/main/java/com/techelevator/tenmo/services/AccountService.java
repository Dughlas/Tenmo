package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//we created this
public class AccountService {
    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken;


    public AccountService(String url){
        this.baseUrl = url;
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

    public List<User> getAllAccountsById(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "tenmo/user_id/", HttpMethod.GET,
                entity, User[].class);
        User[] accounts = response.getBody();
        return new ArrayList<>(Arrays.asList(accounts));
    }

    //current work:
    public Account getSingleAccountById(int id) {
        Account account = null;

        try{
            ResponseEntity<Account> result = restTemplate.exchange(baseUrl + "transferfrom/" + id, HttpMethod.GET, makeAuthEntity(), Account.class);
        account = result.getBody();
        }catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage() + "IS this catching something.");
        }
        return account;
    }

    public HttpEntity<Void>makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}