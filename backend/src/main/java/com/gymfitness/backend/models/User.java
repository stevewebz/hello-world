package com.gymfitness.backend.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

@Entity(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private Date dateJoined;
    private Boolean cancelled;

    @ManyToMany
    @JoinTable(name="users_levels", joinColumns=@JoinColumn(name="userId"), inverseJoinColumns=@JoinColumn(name="levelId"))
    private Set<UserLevel> userLevel = new HashSet<>();

    @ManyToMany
    @JoinTable(name="users_classes", joinColumns=@JoinColumn(name="userId"), inverseJoinColumns=@JoinColumn(name="classId"))
    private List<GymClass> gymClasses;

    @OneToMany(mappedBy="user")
    private List<Billing> billings;

    @OneToMany(mappedBy = "user")
    private List<GymClass> instructorClass;

    @OneToOne(mappedBy = "user")
    private Waitlist waitlist;

    @OneToOne(mappedBy = "user")
    private Attendance attendance;

    public User(){
    }

    public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
        this.password = password;
        this.dateJoined = new Date();
        this.cancelled = false;
	}

    /**
     * @return the attendance
     */
    public Attendance getAttendance() {
        return attendance;
    }
    /**
     * @param attendance the attendance to set
     */
    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    /**
     * @return the waitlist
     */
    public Waitlist getWaitlist() {
        return waitlist;
    }
    /**
     * @param waitlist the waitlist to set
     */
    public void setWaitlist(Waitlist waitlist) {
        this.waitlist = waitlist;
    }

    /**
     * @return the instructorClass
     */
    public List<GymClass> getInstructorClass() {
        return instructorClass;
    }
    /**
     * @param instructorClass the instructorClass to set
     */
    public void setInstructorClass(List<GymClass> instructorClass) {
        this.instructorClass = instructorClass;
    }

    /**
     * @return the userLevel
     */
    public Set<UserLevel> getUserLevel() {
        return userLevel;
    }
    /**
     * @param userLevel the userLevel to set
     */
    public void setUserLevel(Set<UserLevel> userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * @return the billings
     */
    public List<Billing> getBillings() {
        return billings;
    }
    /**
     * @param billings the billings to set
     */
    public void setBillings(List<Billing> billings) {
        this.billings = billings;
    }

    /**
     * @return the classes
     */
    public List<GymClass> getGymClasses() {
        return gymClasses;
    }
    /**
     * @param classes the classes to set
     */
    public void setGymClasses(List<GymClass> gymClasses) {
        this.gymClasses = gymClasses;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the dateJoined
     */
    public Date getDateJoined() {
        return dateJoined;
    }
    /**
     * @param dateJoined the dateJoined to set
     */
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    /**
     * @return the cancelled
     */
    public Boolean getCancelled() {
        return cancelled;
    }
    /**
     * @param cancelled the cancelled to set
     */
    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}