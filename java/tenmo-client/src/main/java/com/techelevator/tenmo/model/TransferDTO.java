package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {
        private int userIdTO;
        private int userIdFrom;
        private BigDecimal amount;

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

}
