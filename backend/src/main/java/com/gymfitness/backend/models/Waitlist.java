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
    private Long WaitlistId;
    private Integer Position;

    @OneToOne
    @JoinColumn(name="UserId")
    private User User;

    @OneToOne
    @JoinColumn(name="ClassId")
    private GymClass GymClass;

    public Waitlist(){
    }

    /**
     * @return the waitlistId
     */
    public Long getWaitlistId() {
        return WaitlistId;
    }
    /**
     * @param waitlistId the waitlistId to set
     */
    public void setWaitlistId(Long waitlistId) {
        WaitlistId = waitlistId;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return Position;
    }
    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        Position = position;
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
     * @return the class
     */
    public GymClass getGymClass(){
        return GymClass;
    }
    /**
     * @param class1 the class to set
     */
    public void setGymClass(GymClass gymClass) {
        GymClass = gymClass;
    }
}