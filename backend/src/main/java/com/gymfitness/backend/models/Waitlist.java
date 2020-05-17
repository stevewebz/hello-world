package com.gymfitness.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "waitlists")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Waitlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waitlistId;
    private Integer position;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToOne
    @JoinColumn(name="classId")
    private GymClass gymClass;

    public Waitlist(){
    }

    /**
     * @return the waitlistId
     */
    public Long getWaitlistId() {
        return waitlistId;
    }
    /**
     * @param waitlistId the waitlistId to set
     */
    public void setWaitlistId(Long waitlistId) {
        this.waitlistId = waitlistId;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }
    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
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
     * @return the class
     */
    public GymClass getGymClass(){
        return gymClass;
    }
    /**
     * @param class1 the class to set
     */
    public void setGymClass(GymClass gymClass) {
        this.gymClass = gymClass;
    }
}