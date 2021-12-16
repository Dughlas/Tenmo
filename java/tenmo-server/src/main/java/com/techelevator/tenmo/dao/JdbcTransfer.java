package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcTransfer implements TransferDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransfer(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferDTO findSenderByID(int userIdTo) {
        String sql = "SELECT * FROM accounts JOIN users ON users.user_id = accounts.user_id users WHERE users.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userIdTo);
        TransferDTO transferDTO = null;
        if(result.next()){
            transferDTO = mapRowToTransfer(result);
        }
        return transferDTO;
    }

    @Override
    public TransferDTO findReceiverByID(int userIdFrom) {
        String sql = "SELECT * FROM accounts JOIN users ON users.user_id = accounts.user_id WHERE users.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userIdFrom);
        TransferDTO transferDTO = null;
        if(result.next()){
            transferDTO = mapRowToTransfer(result);
        }
        return transferDTO;
    }


    //how to 1: take userId get back account number and have enough money to transfer
    //2: subtract amount from the fromAccount
    //3: add amount to the toAccount
    //4: write something to the transfer table.
    @Override
    public TransferDTO transfer(TransferDTO transfer) {
        System.out.println("I'm in here! Ready to Transfer: ");
        return null;
    }



    private TransferDTO mapRowToTransfer(SqlRowSet results){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setAmount(results.getBigDecimal("balance"));
        transferDTO.setUserIdTO(results.getInt("user_id"));
        transferDTO.setUserIdFrom(results.getInt("user_id"));
        return transferDTO;
    }
}
