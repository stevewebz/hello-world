package com.gymfitness.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "userlevels")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserLevel{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long LevelId;
    private String LevelName;

    @OneToOne(mappedBy = "UserLevel")
    private User User;

    public UserLevel(){
    }

    /**
     * @return the member
     */
    public User getUser() {
        return User;
    }
    /**
     * @param member the member to set
     */
    public void setUser(User user) {
        User = user;
    }

    /**
     * @return the levelId
     */
    public Long getLevelId() {
        return LevelId;
    }
    /**
     * @param levelId the levelId to set
     */
    public void setLevelId(Long levelId) {
        LevelId = levelId;
    }

    /**
     * @return the levelName
     */
    public String getLevelName() {
        return LevelName;
    }
    /**
     * @param levelName the levelName to set
     */
    public void setLevelName(String levelName) {
        LevelName = levelName;
    }
}