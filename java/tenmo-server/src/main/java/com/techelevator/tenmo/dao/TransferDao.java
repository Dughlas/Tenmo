package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferDTO;

import java.math.BigDecimal;

public interface TransferDao {
    TransferDTO findSenderByID(int userIdTo);
    TransferDTO findReceiverByID(int userIdFrom);
    TransferDTO transfer(TransferDTO transfer);
}
