package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.TransferStatusesDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.TransferStatuses;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

//@RequestMapping("transfer")
@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {
    @Autowired
    private TransferDao transferDao;
    @Autowired
    private TransferStatusesDao transferStatusesDao;

    @RequestMapping(path = "/transferto/{userIdTo}", method = RequestMethod.GET)
    public TransferDTO transferToUserId(@PathVariable int userIdTo){//returns userIdTo
        System.out.println("from TransferController around line 26 logged in user Id: " + userIdTo);
        return transferDao.findReceiverByID(userIdTo);
    }
    @PostMapping("transfer")  //get userToId, get userFromId, get amount
    public TransferDTO transferAmount(@RequestBody TransferDTO transferDTO){
        System.out.println(transferDTO);
        return transferDao.transfer(transferDTO);
    }
    @GetMapping("transfer")  //currently just returns transferStatusDesc in postman - approved
    public TransferStatuses transferStatus(Principal principal){
                return transferStatusesDao.transferStatusDesc(principal);
    }
    @GetMapping("transfer/all") // returns a full list on the table in postman, but only "2"
    // from jdbcTransferDao findAllTransfers - line 119ish: 2
    public List<Transfer> findAllTransfers(){
        return  transferDao.findAllTransfers();
    }

}
//