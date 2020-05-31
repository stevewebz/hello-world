package com.gymfitness.backend.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "locations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long locationId;
    private String locationName;

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<GymClass> gymClasses;

    public Location(){
    }

    /**
     * @return the locationId
     */
    public Long getLocationId() {
        return locationId;
    }
    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }
    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return the classes
     */
    public List<GymClass> getGymClasses() {
        return gymClasses;
    }
    /**
     * @param classes the classes to set
     */
    public void setGymClasses(List<GymClass> gymClasses) {
        this.gymClasses = gymClasses;
    }
}