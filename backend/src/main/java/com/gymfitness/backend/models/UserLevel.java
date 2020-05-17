package com.gymfitness.backend.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "userlevels")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserLevel{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long levelId;

    @Enumerated(EnumType.STRING)
    private EnumLevel levelName;

    public UserLevel(){
    }

    public UserLevel(EnumLevel levelName){
        this.levelName = levelName;
    }

    /**
     * @return the levelId
     */
    public Long getLevelId() {
        return levelId;
    }
    /**
     * @param levelId the levelId to set
     */
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    /**
     * @return the levelName
     */
    public EnumLevel getLevelName() {
        return levelName;
    }
    /**
     * @param levelName the levelName to set
     */
    public void setLevelName(EnumLevel levelName) {
        this.levelName = levelName;
    }
}