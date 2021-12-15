package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private JdbcAccountDao jdbcAccountDao;

    @RequestMapping(path = "/balance/{username}", method = RequestMethod.GET)
    public String getBalanceByUserName(@PathVariable String username){
        return jdbcAccountDao.findBalance(username);
    }

}
