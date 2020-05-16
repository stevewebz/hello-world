package com.gymfitness.backend.controllers;

import java.util.List;
import com.gymfitness.backend.models.GymClass;
import com.gymfitness.backend.repositories.GymClassRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classes")
public class ClassController {
    @Autowired
    private GymClassRepository gymClassRepository;

    @GetMapping
    public List<GymClass> list(){
        return gymClassRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public GymClass get(@PathVariable Long id){
        return gymClassRepository.getOne(id);
    }

    @PostMapping
    public GymClass create(@RequestBody final GymClass gymClass){
        return gymClassRepository.saveAndFlush(gymClass);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        gymClassRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public GymClass update(@PathVariable Long id, @RequestBody GymClass gymClass){
        //put expects all, patch doesnt
        //todo: check all variables are here
        GymClass existingGymClass = gymClassRepository.getOne(id);
        BeanUtils.copyProperties(gymClass, existingGymClass, "ClassId");
        return gymClassRepository.saveAndFlush(existingGymClass);
    }
}