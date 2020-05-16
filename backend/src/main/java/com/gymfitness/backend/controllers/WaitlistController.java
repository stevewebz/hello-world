package com.gymfitness.backend.controllers;

import java.util.List;

import com.gymfitness.backend.models.Waitlist;
import com.gymfitness.backend.repositories.WaitlistRepository;

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
@RequestMapping("/api/waitlist")
public class WaitlistController {
    @Autowired
    private WaitlistRepository waitlistRepository;

    @GetMapping
    public List<Waitlist> list(){
        return waitlistRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Waitlist get(@PathVariable Long id){
        return waitlistRepository.getOne(id);
    }

    @PostMapping
    public Waitlist create(@RequestBody final Waitlist waitlist){
        return waitlistRepository.saveAndFlush(waitlist);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        waitlistRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public Waitlist update(@PathVariable Long id, @RequestBody Waitlist waitlist){
        //put expects all, patch doesnt
        //todo: check all variables are here
        Waitlist existingWaitlist = waitlistRepository.getOne(id);
        BeanUtils.copyProperties(waitlist, existingWaitlist, "LevelId");
        return waitlistRepository.saveAndFlush(existingWaitlist);
    }
}