package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    //might be a HttpMethod.post/put
//    public Transfer transferFrom(String token){
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(token);
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//        ResponseEntity<Transfer> response = restTemplate.exchange(baseUrl + "transferto/" + token,
//                HttpMethod.GET, entity, Transfer.class);
//        Transfer transfer = response.getBody();
//        return transfer;
//    }
    //

    public TransferDTO sendTransfer(int fromId, int toId, BigDecimal amount){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setUserIdFrom(fromId);
        transferDTO.setUserIdTO(toId);
        transferDTO.setAmount(amount);
        TransferDTO transfer = restTemplate.postForObject(baseUrl + "transfer", makeTransferDTOAuthEntity(transferDTO), TransferDTO.class);
        return transfer;
    }
    // public List<User> getAllAccountsById(String token){
    //        HttpHeaders headers = new HttpHeaders();
    //        headers.setBearerAuth(token);
    //        HttpEntity<Void> entity = new HttpEntity<>(headers);
    //        ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "tenmo/user_id/", HttpMethod.GET,
    //                entity, User[].class);
    //        User[] accounts = response.getBody();
    //        return new ArrayList<>(Arrays.asList(accounts));
    //    }

    public Transfer getStatusResponse(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Transfer> response = restTemplate.exchange(baseUrl + "transfer", HttpMethod.GET,
                entity, Transfer.class);
        Transfer accounts = response.getBody();
        return accounts;
    }
    //hopefully calling all transferId's
    public List<Transfer> getAllTransferIds(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Transfer[]> response = restTemplate.exchange(baseUrl + "transfer/all", HttpMethod.GET,
                entity, Transfer[].class);
        Transfer[] transferIds = response.getBody();
        return new ArrayList<>(Arrays.asList(transferIds));
    }

    public HttpEntity<TransferDTO> makeTransferDTOAuthEntity(TransferDTO transferDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        HttpEntity<TransferDTO> entity = new HttpEntity<>(transferDTO, headers);
        return entity;
    }


}
