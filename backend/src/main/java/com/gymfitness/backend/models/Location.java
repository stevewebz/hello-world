package com.gymfitness.backend.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "locations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long LocationId;
    private String LocationName;

    @OneToMany(mappedBy = "Location")
    private List<GymClass> GymClasses;

    public Location(){
    }

    /**
     * @return the locationId
     */
    public Long getLocationId() {
        return LocationId;
    }
    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(Long locationId) {
        LocationId = locationId;
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return LocationName;
    }
    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    /**
     * @return the classes
     */
    public List<GymClass> getGymClasses() {
        return GymClasses;
    }
    /**
     * @param classes the classes to set
     */
    public void setClasses(List<GymClass> gymClasses) {
        GymClasses = gymClasses;
    }
}