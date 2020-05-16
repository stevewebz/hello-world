package com.gymfitness.backend.controllers;

import java.util.List;
import com.gymfitness.backend.models.MemberLevel;
import com.gymfitness.backend.repositories.MemberLevelRepository;

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
@RequestMapping("/api/member/levels")
public class MemberLevelController {
    @Autowired
    private MemberLevelRepository memberLevelRepository;

    @GetMapping
    public List<MemberLevel> list(){
        return memberLevelRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public MemberLevel get(@PathVariable Long id){
        return memberLevelRepository.getOne(id);
    }

    @PostMapping
    public MemberLevel create(@RequestBody final MemberLevel memberLevel){
        return memberLevelRepository.saveAndFlush(memberLevel);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        memberLevelRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public MemberLevel update(@PathVariable Long id, @RequestBody MemberLevel memberLevel){
        //put expects all, patch doesnt
        //todo: check all variables are here
        MemberLevel existingMemberLevel = memberLevelRepository.getOne(id);
        BeanUtils.copyProperties(memberLevel, existingMemberLevel, "MemberId");
        return memberLevelRepository.saveAndFlush(existingMemberLevel);
    }
}