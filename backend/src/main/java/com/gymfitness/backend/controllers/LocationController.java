package com.gymfitness.backend.controllers;

import java.util.List;

import com.gymfitness.backend.models.Location;
import com.gymfitness.backend.repositories.LocationRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/all")
    public List<Location> allLocations(){
        List<Location> allLocations = locationRepository.findAll();
        
        return allLocations;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id){
        return locationRepository.getOne(id);
    }

    @PostMapping
    public Location create(@RequestBody final Location location){
        return locationRepository.saveAndFlush(location);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        locationRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public Location update(@PathVariable Long id, @RequestBody Location location){
        //put expects all, patch doesnt
        //todo: check all variables are here
        Location existingLocation = locationRepository.getOne(id);
        BeanUtils.copyProperties(location, existingLocation, "LocationId");
        return locationRepository.saveAndFlush(existingLocation);
    }
}