package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {
    @Autowired
    private AccountDao jdbcAccountDao;
    @Autowired
    private UserDao userDao;


    @RequestMapping(path = "/balance/{username}", method = RequestMethod.GET)
    public Account getBalanceByUserName(@PathVariable String username){
        System.out.println(username);
        return jdbcAccountDao.findBalance(username);
    }
//
//    @RequestMapping(path = "/user_id/", method = RequestMethod.GET)
//    public List<Account> getListOfUserIds(){
//        return jdbcAccountDao.getAllAccountsById();
//    }
    @GetMapping("/tenmo/user_id/")
    public List<User> findAll(){
        return userDao.findAll();
    }

    @GetMapping("/user_id/")
    public List<User> findAllExceptCurrentUser(Principal principal){
        return userDao.findAllExceptCurrentUser(principal.getName());
    }
}
