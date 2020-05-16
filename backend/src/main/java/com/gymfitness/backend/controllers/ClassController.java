package com.gymfitness.backend.controllers;

import java.util.List;
import com.gymfitness.backend.models.Class;
import com.gymfitness.backend.repositories.ClassRepository;

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
    private ClassRepository classRepository;

    @GetMapping
    public List<Class> list(){
        return classRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Class get(@PathVariable Long id){
        return classRepository.getOne(id);
    }

    @PostMapping
    public Class create(@RequestBody final Class _class){
        return classRepository.saveAndFlush(_class);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        classRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public Class update(@PathVariable Long id, @RequestBody Class _class){
        //put expects all, patch doesnt
        //todo: check all variables are here
        Class existingClass = classRepository.getOne(id);
        BeanUtils.copyProperties(_class, existingClass, "MemberId");
        return classRepository.saveAndFlush(existingClass);
    }
}