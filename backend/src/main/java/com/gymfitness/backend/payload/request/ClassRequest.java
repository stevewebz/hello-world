package com.gymfitness.backend.payload.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ClassRequest {
    @NotNull(message = "Please provide user id")
    private Long userId;

    private Long classId;

    private String className;

    private Date dateTime;

    private Integer maxCapacity;

    private Long locationId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userid) {
        this.userId = userid;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classid) {
        this.classId = classid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}