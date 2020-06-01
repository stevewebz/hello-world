package com.gymfitness.backend.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "gymclasses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    private String className;
    private Integer maxCapacity;
    private Integer totalEnrolled;
    private Date dateTime;

    @ManyToMany(mappedBy="gymClasses")
    @JsonIgnore
    private List<User> users;

    @ManyToOne
    @JoinColumn(name="locationId", referencedColumnName = "locationId")
    private Location location;

    @ManyToOne
    @JoinColumn(name="instructorId", referencedColumnName = "userId")
    private User instructor;

    @OneToOne(mappedBy = "gymClass")
    private Waitlist waitlist;

    @OneToOne(mappedBy = "gymClass")
    private Attendance attendance;

    public GymClass(){
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

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }
    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }
    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return the classId
     */
    public Long getClassId() {
        return classId;
    }
    /**
     * @param classId the classId to set
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the maxCapacity
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }
    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * @return the totalEnrolled
     */
    public Integer getTotalEnrolled() {
        return totalEnrolled;
    }
    /**
     * @param totalEnrolled the totalEnrolled to set
     */
    public void setTotalEnrolled(Integer totalEnrolled) {
        this.totalEnrolled = totalEnrolled;
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

}