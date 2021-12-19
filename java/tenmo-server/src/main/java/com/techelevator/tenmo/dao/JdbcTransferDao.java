package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferDTO findSenderByID(int userIdTo) {
        String sql = "SELECT * FROM accounts JOIN users ON users.user_id = accounts.user_id users WHERE users.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userIdTo);
        TransferDTO transferDTO = null;
        if(result.next()){
            transferDTO = mapRowToTransferDto(result);
        }
        return transferDTO;
    }

    @Override
    public TransferDTO findReceiverByID(int userIdFrom) {
        String sql = "SELECT * FROM accounts JOIN users ON users.user_id = accounts.user_id WHERE users.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userIdFrom);
        TransferDTO transferDTO = null;
        if(result.next()){
            transferDTO = mapRowToTransferDto(result);
        }
        return transferDTO;
    }


    //how to 1: take userId get back account number and have enough money to transfer
    //2: subtract amount from the fromAccount
    //3: add amount to the toAccount
    //4: write something to the transfer table.
    @Override
    public TransferDTO transfer(TransferDTO transfer) {
        BigDecimal fromAccountBalance = new BigDecimal("0");
        BigDecimal moneySent = transfer.getAmount();
        BigDecimal newToAccountBalance = new BigDecimal("0");
        BigDecimal bigDecimalBalance = new BigDecimal("0");
        String sql = "SELECT balance FROM accounts WHERE user_id = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transfer.getUserIdFrom());
        if (result.next()) {
            double newBalance = result.getDouble("balance");
            bigDecimalBalance = new BigDecimal(newBalance);
            System.out.println("initial balance: " + bigDecimalBalance);
        }
        if (bigDecimalBalance.compareTo(moneySent) >= 0) {
            fromAccountBalance = bigDecimalBalance.subtract(moneySent);
            System.out.println("new balance: " + fromAccountBalance);


            String sql1 = "SELECT balance FROM accounts WHERE user_id = ?";
            SqlRowSet result1 = jdbcTemplate.queryForRowSet(sql1, transfer.getUserIdTO());
            if (result1.next()) {
                double newBalance = result1.getDouble("balance");
                BigDecimal toAccountBalance = new BigDecimal(newBalance);
                newToAccountBalance = toAccountBalance.add(moneySent);
                System.out.println("balanace 2: " + toAccountBalance);
            }

            String sql3 = "UPDATE accounts SET balance = ? WHERE user_id = ?";
            jdbcTemplate.update(sql3, newToAccountBalance, transfer.getUserIdTO());

            String sql2 = "UPDATE accounts SET balance = ? WHERE user_id = ?";
            jdbcTemplate.update(sql2, fromAccountBalance, transfer.getUserIdFrom());

            String sql4 = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_to, account_from, amount) " +
                    "VALUES (2, 2, (SELECT account_id FROM accounts WHERE user_id = ?), (SELECT account_id FROM accounts WHERE user_id = ?), ?) ";
            jdbcTemplate.update(sql4, transfer.getUserIdTO(), transfer.getUserIdFrom(), moneySent);
            }


        return null;
    }

    @Override
    public Transfer ResponseStatusId(Transfer transfer) {
        String sql5 = "SELECT transfer_status_desc FROM transfer_statuses " +
                "JOIN transfers ON transfers.transfer_status_id = transfer_statuses.transfer_status_id " +
                "WHERE account_from = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql5, transfer.getAccountFrom());
        System.out.println(transfer.getAccountFrom());
        System.out.println("are we in Jdbc ResponseStatus?");
        Transfer transferStatus = null;
        if (results.next()) {
            transferStatus = mapRowToTransfer(results);
        }
        System.out.println("response status: " + transferStatus);
        return transferStatus;
    }


    @Override
    public List<Transfer> findAllTransfers() {
        List<Transfer> transferStatus = new ArrayList<>();
        String sql = "SELECT * FROM transfers ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transferStatus.add(transfer);
        }
        return transferStatus;
    }




    private TransferDTO mapRowToTransferDto(SqlRowSet results){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setAmount(results.getBigDecimal("balance"));
        transferDTO.setUserIdTO(results.getInt("user_id"));
        transferDTO.setUserIdFrom(results.getInt("user_id"));
        return transferDTO;
    }
    private Transfer mapRowToTransfer(SqlRowSet results){
        Transfer transfer = new Transfer();
        transfer.setAmount(results.getBigDecimal("amount"));
        transfer.setAccountTo(results.getInt("account_to"));
        transfer.setAccountFrom(results.getInt("account_from"));
        transfer.setTransferStatus(results.getInt("transfer_status_id"));
        transfer.setTransferType(results.getInt("transfer_type_id"));
        transfer.setTransferId(results.getInt("transfer_id"));
        return transfer;
    }



}
