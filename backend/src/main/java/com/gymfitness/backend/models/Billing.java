package com.gymfitness.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "billings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Billing{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long BillingId;
    private Integer BankNo;
    private Integer ClearingNo;

    @ManyToOne
    @JoinColumn(name="MemberId", referencedColumnName = "MemberId")
    private User User;

    public Billing(){
    }

    /**
     * @return the billingId
     */
    public Long getBillingId() {
        return BillingId;
    }
    /**
     * @param billingId the billingId to set
     */
    public void setBillingId(Long billingId) {
        BillingId = billingId;
    }

    /**
     * @return the bankNo
     */
    public Integer getBankNo() {
        return BankNo;
    }
    /**
     * @param bankNo the bankNo to set
     */
    public void setBankNo(Integer bankNo) {
        BankNo = bankNo;
    }

    /**
     * @return the clearingNo
     */
    public Integer getClearingNo() {
        return ClearingNo;
    }
    /**
     * @param clearingNo the clearingNo to set
     */
    public void setClearingNo(Integer clearingNo) {
        ClearingNo = clearingNo;
    }

    /**
     * @return the member
     */
    public User getUser() {
        return User;
    }
    /**
     * @param member the member to set
     */
    public void setUser(User user) {
        User = user;
    }
}