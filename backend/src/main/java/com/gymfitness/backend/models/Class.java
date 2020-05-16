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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "classes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClassId;
    private String ClassName;
    private Integer MaxCapacity;
    private Integer TotalEnrolled;
    private Date DateTime;

    @ManyToMany(mappedBy="Classes")
    @JsonIgnore
    private List<User> Users;

    @ManyToOne
    @JoinColumn(name="LocationId", referencedColumnName = "LocationId")
    private Location Location;

    public Class(){
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return Location;
    }
    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        Location = location;
    }

    /**
     * @return the members
     */
    public List<User> getUsers() {
        return Users;
    }
    /**
     * @param members the members to set
     */
    public void setUsers(List<User> users) {
        Users = users;
    }

    /**
     * @return the classId
     */
    public Long getClassId() {
        return ClassId;
    }
    /**
     * @param classId the classId to set
     */
    public void setClassId(Long classId) {
        ClassId = classId;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return ClassName;
    }
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        ClassName = className;
    }

    /**
     * @return the maxCapacity
     */
    public Integer getMaxCapacity() {
        return MaxCapacity;
    }
    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(Integer maxCapacity) {
        MaxCapacity = maxCapacity;
    }

    /**
     * @return the totalEnrolled
     */
    public Integer getTotalEnrolled() {
        return TotalEnrolled;
    }
    /**
     * @param totalEnrolled the totalEnrolled to set
     */
    public void setTotalEnrolled(Integer totalEnrolled) {
        TotalEnrolled = totalEnrolled;
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

}