package com.gymfitness.backend.payload.request;

public class BillingRequest {
    private Long billingId;

    private Integer bankNo;

    private Integer clearingNo;

    private String email;

    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    public Integer getBankNo() {
        return bankNo;
    }

    public void setBankNo(Integer bankNo) {
        this.bankNo = bankNo;
    }

    public Integer getClearingNo() {
        return clearingNo;
    }

    public void setClearingNo(Integer clearingNo) {
        this.clearingNo = clearingNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}