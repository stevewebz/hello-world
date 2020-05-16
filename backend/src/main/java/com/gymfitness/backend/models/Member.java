package com.gymfitness.backend.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "members")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberId;
    private String Username;
    private String Password;
    private String Email;
    private Long BillingId;
    private Long LevelId;

    @ManyToMany
    @JoinTable(name="members_classes", joinColumns=@JoinColumn(name="MemberId"), inverseJoinColumns=@JoinColumn(name="ClassId"))
    private List<Class> Classes;

    public Member(){
    }

    /**
     * @return the classes
     */
    public List<Class> getClasses() {
        return Classes;
    }
    /**
     * @param classes the classes to set
     */
    public void setClasses(List<Class> classes) {
        Classes = classes;
    }

    /**
     * @return the memberId
     */
    public Long getMemberId() {
        return MemberId;
    }
    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(Long memberId) {
        MemberId = memberId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return Username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        Username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return Password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return Email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        Email = email;
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
     * @return the levelId
     */
    public Long getLevelId() {
        return LevelId;
    }
    /**
     * @param levelId the levelId to set
     */
    public void setLevelId(Long levelId) {
        LevelId = levelId;
    }
}