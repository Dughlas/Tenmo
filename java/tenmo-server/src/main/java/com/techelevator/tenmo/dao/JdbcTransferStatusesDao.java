package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.TransferStatuses;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JdbcTransferStatusesDao implements TransferStatusesDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferStatusesDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransferStatuses transferStatusDesc(Principal principal) {
        String sql5 = "SELECT transfer_status_desc FROM transfer_statuses ts\n" +
                "JOIN transfers t ON t.transfer_Status_id = ts.transfer_status_id\n" +
                "JOIN accounts a ON a.account_id = t.account_from OR a.account_id = t.account_to\n" +
                "JOIN users u ON u.user_id = a.user_id\n" +
                "WHERE u.username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql5, principal.getName());
        TransferStatuses transferStatuses = null;
        while (results.next()) {
            transferStatuses = mapRowToTransferStatus(results);

        }

        System.out.println("jdbcTransferStatusesDao says: " + transferStatuses.getTransferStatusDesc());
        return transferStatuses;
    }



    @Override
    public TransferStatuses transferStatusId(TransferStatuses transferStatusId) {
        return null;
    }

    private TransferStatuses mapRowToTransferStatus(SqlRowSet results){
        TransferStatuses transferStatuses = new TransferStatuses();
        transferStatuses.setTransferStatusDesc(results.getString("transfer_status_desc"));
        return transferStatuses;
    }
}
