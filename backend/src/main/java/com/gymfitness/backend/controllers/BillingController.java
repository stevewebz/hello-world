package com.gymfitness.backend.controllers;

import com.gymfitness.backend.repositories.BillingRepository;
import java.util.List;
import com.gymfitness.backend.models.Billing;
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
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    private BillingRepository billingRepository;

    @GetMapping
    public List<Billing> list(){
        return billingRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Billing get(@PathVariable Long id){
        return billingRepository.getOne(id);
    }

    @PostMapping
    public Billing create(@RequestBody final Billing billing){
        return billingRepository.saveAndFlush(billing);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        billingRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public Billing update(@PathVariable Long id, @RequestBody Billing billing){
        //put expects all, patch doesnt
        //todo: check all variables are here
        Billing existingBilling = billingRepository.getOne(id);
        BeanUtils.copyProperties(billing, existingBilling, "BillingId");
        return billingRepository.saveAndFlush(existingBilling);
    }
    
}