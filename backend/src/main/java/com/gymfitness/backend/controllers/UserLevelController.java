package com.gymfitness.backend.controllers;

import java.util.List;
import com.gymfitness.backend.models.UserLevel;
import com.gymfitness.backend.repositories.UserLevelRepository;

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
@RequestMapping("/api/user/levels")
public class UserLevelController {
    @Autowired
    private UserLevelRepository userLevelRepository;

    @GetMapping
    public List<UserLevel> list(){
        return userLevelRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public UserLevel get(@PathVariable Long id){
        return userLevelRepository.getOne(id);
    }

    @PostMapping
    public UserLevel create(@RequestBody final UserLevel userLevel){
        return userLevelRepository.saveAndFlush(userLevel);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        userLevelRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public UserLevel update(@PathVariable Long id, @RequestBody UserLevel userLevel){
        //put expects all, patch doesnt
        //todo: check all variables are here
        UserLevel existingUserLevel = userLevelRepository.getOne(id);
        BeanUtils.copyProperties(userLevel, existingUserLevel, "LevelId");
        return userLevelRepository.saveAndFlush(existingUserLevel);
    }
}