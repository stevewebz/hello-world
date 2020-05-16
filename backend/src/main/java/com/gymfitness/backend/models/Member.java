package com.gymfitness.backend.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

    @OneToOne
    @JoinColumn(name="LevelId")
    private MemberLevel MemberLevel;

    @ManyToMany
    @JoinTable(name="members_classes", joinColumns=@JoinColumn(name="MemberId"), inverseJoinColumns=@JoinColumn(name="ClassId"))
    private List<Class> Classes;

    @OneToMany(mappedBy="Member")
    private List<Billing> Billings;

    public Member(){
    }

    /**
     * @return the memberLevel
     */
    public MemberLevel getMemberLevel() {
        return MemberLevel;
    }
    /**
     * @param memberLevel the memberLevel to set
     */
    public void setMemberLevel(MemberLevel memberLevel) {
        MemberLevel = memberLevel;
    }

    /**
     * @return the billings
     */
    public List<Billing> getBillings() {
        return Billings;
    }
    /**
     * @param billings the billings to set
     */
    public void setBillings(List<Billing> billings) {
        Billings = billings;
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
}