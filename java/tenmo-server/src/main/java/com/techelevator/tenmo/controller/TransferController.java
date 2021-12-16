package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {
    @Autowired
    private TransferDao transferDao;

    @RequestMapping(path = "/transferto/{userIdTo}", method = RequestMethod.GET)
    public TransferDTO transferToUserId(@PathVariable int userIdTo){
        System.out.println(userIdTo);
        return transferDao.findReceiverByID(userIdTo);
    }
    @PostMapping("transfer")
    public TransferDTO transferAmount(@RequestBody TransferDTO transferDTO){
        return transferDao.transfer(transferDTO);
    }

}
