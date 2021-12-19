package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    TransferDTO findSenderByID(int userIdTo);
    TransferDTO findReceiverByID(int userIdFrom);
    TransferDTO transfer(TransferDTO transfer);
    Transfer ResponseStatusId(Transfer transferId);
    Transfer transferStatusDesc(TransferDTO transferStatus);
    List<Transfer> findAllTransfers();
}
