package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {
    @Autowired
    private AccountDao jdbcAccountDao;

    @RequestMapping(path = "/balance/{username}", method = RequestMethod.GET)
    public Account getBalanceByUserName(@PathVariable String username){
        System.out.println(username);
        return jdbcAccountDao.findBalance(username);
    }

    @RequestMapping(path = "/user_id/", method = RequestMethod.GET)
    public List<Account> getListOfUserIds(){
        return jdbcAccountDao.getAllAccountsById();
    }

}
