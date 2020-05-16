package com.gymfitness.backend.controllers;

import java.util.List;
import com.gymfitness.backend.models.Member;
import com.gymfitness.backend.repositories.MemberRepository;

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
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public List<Member> list(){
        return memberRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Member get(@PathVariable Long id){
        return memberRepository.getOne(id);
    }

    @PostMapping
    public Member create(@RequestBody final Member member){
        return memberRepository.saveAndFlush(member);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //check for fk constraints.. add way to delete fk also
        memberRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public Member update(@PathVariable Long id, @RequestBody Member member){
        //put expects all, patch doesnt
        //todo: check all variables are here
        Member existingMember = memberRepository.getOne(id);
        BeanUtils.copyProperties(member, existingMember, "MemberId");
        return memberRepository.saveAndFlush(existingMember);
    }
}