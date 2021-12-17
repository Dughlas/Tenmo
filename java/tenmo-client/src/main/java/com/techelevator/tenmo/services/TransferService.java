package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
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

    public TransferDTO sendTransfer(int fromId, int toId, BigDecimal amount){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setUserIdFrom(fromId);
        transferDTO.setUserIdTO(toId);
        transferDTO.setAmount(amount);
        TransferDTO transfer = restTemplate.postForObject(baseUrl + "transfer", makeTransferDTOAuthEntity(transferDTO), TransferDTO.class);
        return transfer;
    }


    public HttpEntity<TransferDTO> makeTransferDTOAuthEntity(TransferDTO transferDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        HttpEntity<TransferDTO> entity = new HttpEntity<>(transferDTO, headers);
        return entity;
    }

}
