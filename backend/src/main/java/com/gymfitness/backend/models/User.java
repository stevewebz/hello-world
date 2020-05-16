package com.gymfitness.backend.models;

import java.util.Date;
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

@Entity(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;
    private String Username;
    private String Password;
    private String Email;
    private Date DateJoined;

    @OneToOne
    @JoinColumn(name="LevelId")
    private UserLevel UserLevel;

    @ManyToMany
    @JoinTable(name="users_classes", joinColumns=@JoinColumn(name="UserId"), inverseJoinColumns=@JoinColumn(name="ClassId"))
    private List<GymClass> GymClasses;

    @OneToMany(mappedBy="User")
    private List<Billing> Billings;

    @OneToMany(mappedBy = "User")
    private List<GymClass> InstructorClass;

    @OneToOne(mappedBy = "User")
    private Waitlist Waitlist;

    @OneToOne(mappedBy = "User")
    private Attendance Attendance;

    public User(){
    }

    /**
     * @return the attendance
     */
    public Attendance getAttendance() {
        return Attendance;
    }
    /**
     * @param attendance the attendance to set
     */
    public void setAttendance(Attendance attendance) {
        Attendance = attendance;
    }

    /**
     * @return the waitlist
     */
    public Waitlist getWaitlist() {
        return Waitlist;
    }
    /**
     * @param waitlist the waitlist to set
     */
    public void setWaitlist(Waitlist waitlist) {
        Waitlist = waitlist;
    }

    /**
     * @return the instructorClass
     */
    public List<GymClass> getInstructorClass() {
        return InstructorClass;
    }
    /**
     * @param instructorClass the instructorClass to set
     */
    public void setInstructorClass(List<GymClass> instructorClass) {
        InstructorClass = instructorClass;
    }

    /**
     * @return the userLevel
     */
    public UserLevel getUserLevel() {
        return UserLevel;
    }
    /**
     * @param userLevel the userLevel to set
     */
    public void setUserLevel(UserLevel userLevel) {
        UserLevel = userLevel;
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
    public List<GymClass> getGymClasses() {
        return GymClasses;
    }
    /**
     * @param classes the classes to set
     */
    public void setGymClasses(List<GymClass> gymClasses) {
        GymClasses = gymClasses;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return UserId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        UserId = userId;
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
     * @return the dateJoined
     */
    public Date getDateJoined() {
        return DateJoined;
    }
    /**
     * @param dateJoined the dateJoined to set
     */
    public void setDateJoined(Date dateJoined) {
        DateJoined = dateJoined;
    }
}