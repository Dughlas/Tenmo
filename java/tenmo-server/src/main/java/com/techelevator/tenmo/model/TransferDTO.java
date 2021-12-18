package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {
    private int userIdTO;
    private int userIdFrom;
    private BigDecimal amount;
    private String ResponseStatusDesc;//transfer_statuses	transfer_status_desc	12	varchar
    private int transferStatusId; //transfer_statuses	transfer_status_id	4	serial



    public int getUserIdTO() {
        return userIdTO;
    }

    public void setUserIdTO(int userIdTO) {
        this.userIdTO = userIdTO;
    }

    public int getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(int userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getResponseStatusDesc() {
        return ResponseStatusDesc;
    }

    public void setResponseStatusDesc(String responseStatusDesc) {
        ResponseStatusDesc = responseStatusDesc;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }
}