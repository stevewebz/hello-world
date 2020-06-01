package com.gymfitness.backend.controllers;

import com.gymfitness.backend.repositories.BillingRepository;
import java.util.List;

import javax.validation.Valid;

import com.gymfitness.backend.models.Billing;
import com.gymfitness.backend.payload.request.BillingRequest;
import com.gymfitness.backend.payload.response.MessageResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    private BillingRepository billingRepository;

    @GetMapping("/all")
    public List<Billing> list(){
        return billingRepository.findAll();
    }

    @PostMapping("/delete")
	public ResponseEntity<?> changeUserPassword(@Valid @RequestBody BillingRequest request) {
        Billing billing = billingRepository.findByBillingId(request.getBillingId());
        
        billingRepository.delete(billing);

		return ResponseEntity.ok(new MessageResponse("Billing deleted!"));
	}

    @GetMapping
    @RequestMapping("/{id}")
    public List<Billing> get(@PathVariable Long id){
        return billingRepository.findByUserUserId(id);
    }

    @PostMapping("/create")
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