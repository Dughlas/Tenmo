package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    TransferDTO findSenderByID(int userIdTo);
    TransferDTO findReceiverByID(int userIdFrom);
    TransferDTO transfer(TransferDTO transfer);
    TransferDTO ResponseStatusId(TransferDTO transferId);
    TransferDTO transferStatusDesc(TransferDTO transferStatus);
    List<TransferDTO> findAllTransfers();
}
