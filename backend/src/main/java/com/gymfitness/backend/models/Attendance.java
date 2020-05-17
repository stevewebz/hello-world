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
    private Long attendanceId;
    private Date dateTime;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToOne
    @JoinColumn(name="classId")
    private GymClass gymClass;

    public Attendance(){
    }

    /**
     * @return the attendanceId
     */
    public Long getAttendanceId() {
        return attendanceId;
    }
    /**
     * @param attendanceId the attendanceId to set
     */
    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }
    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

    /**
     * @return the gymClass
     */
    public GymClass getGymClass() {
        return gymClass;
    }
    /**
     * @param gymClass the gymClass to set
     */
    public void setGymClass(GymClass gymClass) {
        this.gymClass = gymClass;
    }
}