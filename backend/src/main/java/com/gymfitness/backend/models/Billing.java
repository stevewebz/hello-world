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
    private Long billingId;
    private Integer bankNo;
    private Integer clearingNo;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User user;

    public Billing(){
    }

    /**
     * @return the billingId
     */
    public Long getBillingId() {
        return billingId;
    }
    /**
     * @param billingId the billingId to set
     */
    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    /**
     * @return the bankNo
     */
    public Integer getBankNo() {
        return bankNo;
    }
    /**
     * @param bankNo the bankNo to set
     */
    public void setBankNo(Integer bankNo) {
        this.bankNo = bankNo;
    }

    /**
     * @return the clearingNo
     */
    public Integer getClearingNo() {
        return clearingNo;
    }
    /**
     * @param clearingNo the clearingNo to set
     */
    public void setClearingNo(Integer clearingNo) {
        this.clearingNo = clearingNo;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}