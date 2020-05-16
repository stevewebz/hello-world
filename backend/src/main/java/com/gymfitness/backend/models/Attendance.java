package com.gymfitness.backend.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "attendances")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Attendance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long AttendanceId;
    private Date DateTime;

    @OneToOne
    @JoinColumn(name="UserId")
    private User User;

    @OneToOne
    @JoinColumn(name="ClassId")
    private GymClass GymClass;

    public Attendance(){
    }

    /**
     * @return the attendanceId
     */
    public Long getAttendanceId() {
        return AttendanceId;
    }
    /**
     * @param attendanceId the attendanceId to set
     */
    public void setAttendanceId(Long attendanceId) {
        AttendanceId = attendanceId;
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return DateTime;
    }
    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return User;
    }
    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        User = user;
    }

    /**
     * @return the gymClass
     */
    public GymClass getGymClass() {
        return GymClass;
    }
    /**
     * @param gymClass the gymClass to set
     */
    public void setGymClass(GymClass gymClass) {
        GymClass = gymClass;
    }
}