package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
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
        BigDecimal newAccountBalance = new BigDecimal("0");
        BigDecimal moneySent = transfer.getAmount();
        Account account = new Account();
        Account account1 = new Account();
        BigDecimal newToAccountBalance = new BigDecimal("0");
        if(transfer.getUserIdFrom() == transfer.getUserIdFrom()) {
            String sql = "SELECT balance FROM accounts WHERE user_id = ?";

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transfer.getUserIdFrom());
            if (result.next()) {
                double newBalance = result.getDouble("balance");
                BigDecimal bigDecimalBalance = new BigDecimal(newBalance);
                account.setBalance(bigDecimalBalance);
                System.out.println("initial balance: " + account.getBalance());
            }
            if(account.getBalance().compareTo(moneySent) >= 0){
                newAccountBalance = account.getBalance().subtract(moneySent);
                System.out.println("new balance: " + newAccountBalance);

            }

        }
        String sql1 = "SELECT balance FROM accounts WHERE user_id = ?";
        SqlRowSet result1 = jdbcTemplate.queryForRowSet(sql1, transfer.getUserIdTO());
        if(result1.next()) {
            double newBalance = result1.getDouble("balance");
            BigDecimal bigDecimalBalance = new BigDecimal(newBalance);
            account1.setBalance(bigDecimalBalance);
            System.out.println("balanace 2: " + account1.getBalance());
        }
        if(account.getBalance().compareTo(moneySent) >= 0){
            newAccountBalance = account1.getBalance().add(moneySent);
            System.out.println("new balance: " + newAccountBalance);
            String sql3 = "UPDATE accounts SET balance = ? WHERE user_id = ?";
            jdbcTemplate.update(sql3, newToAccountBalance, transfer.getUserIdFrom());
        }

        String sql2 = "UPDATE accounts SET balance = ? WHERE user_id = ?";
        jdbcTemplate.update(sql2, newAccountBalance, transfer.getUserIdTO());

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
