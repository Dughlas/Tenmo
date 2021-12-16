package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//    @Override
//    public List<Account> getAllAccountsById(){
//        List<Account> accountList = new ArrayList<>();
//        String sql = "SELECT * FROM accounts";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while(results.next()){
//            Account account = mapRowToAccount(results);
//            accountList.add(account);
//        }
//        return accountList;
//    }

    // @Override
    //    public List<User> findAll() {
    //        List<User> users = new ArrayList<>();
    //        String sql = "SELECT user_id, username, password_hash FROM users;";
    //        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
    //        while (results.next()) {
    //            User user = mapRowToUser(results);
    //            users.add(user);
    //        }
    //        return users;
    //    }

    @Override
    /**
     * RETURNED NULL FOR NOW
     */
    public Account findAccountById(int accountId) {
        String sql = "SELECT account_id FROM accounts JOIN users ON users.user_id = accounts.user_id WHERE account_id = ? ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);
        Account account = null;
        if(result.next()){
            account = mapRowToAccount(result);
        }
        return null;
    }

    @Override
    public Account findUserById(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        Account account = null;
        if(result.next()){
            account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public Account findBalance(String userName) {
        String sql = "SELECT * FROM accounts JOIN users ON users.user_id = accounts.user_id WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        Account account = null;
        if (results.next()){
            account = mapRowToAccount(results);
        }
        return account;
    }

    private Account mapRowToAccount(SqlRowSet results){
        Account account = new Account();
        account.setAccountId(results.getInt("account_id"));
        account.setUserid(results.getInt("user_id"));
        account.setBalance(results.getBigDecimal("balance"));
        return account;
    }

}