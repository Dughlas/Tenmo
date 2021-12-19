package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.TransferStatuses;

import java.security.Principal;

public interface TransferStatusesDao {
    TransferStatuses transferStatusDesc(Principal principal);
    TransferStatuses transferStatusId(TransferStatuses transferStatusId);

}
