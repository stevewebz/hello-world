package com.gymfitness.backend.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.gymfitness.backend.models.GymClass;
import com.gymfitness.backend.models.User;
import com.gymfitness.backend.payload.request.BookClassRequest;
import com.gymfitness.backend.payload.request.UserRequest;
import com.gymfitness.backend.payload.response.MessageResponse;
import com.gymfitness.backend.repositories.GymClassRepository;
import com.gymfitness.backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
@RequestMapping("/api/classes")
public class ClassController {
    @Autowired
    GymClassRepository gymClassRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<GymClass> list(){
        List<GymClass> allClasses = gymClassRepository.findAll();
        List<GymClass> responseList = new ArrayList<>();
        for (GymClass gymClass : allClasses) {
            Date date1 = new Date();
            Date date2 = gymClass.getDateTime();

            if (date2.compareTo(date1) > 0){
                responseList.add(gymClass);
            }
        }
        return responseList;
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookClass(@Valid @RequestBody BookClassRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        GymClass gymClass = gymClassRepository.findById(request.getClassid())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        Integer totalEnrolled = gymClass.getTotalEnrolled();
        Integer newEnrolled = totalEnrolled + 1;
        gymClass.setTotalEnrolled(newEnrolled);
        gymClassRepository.save(gymClass);

        List<GymClass> gymList = new ArrayList<>();
        gymList.add(gymClass);

        user.setGymClasses(gymList);
        userRepository.save(user);
                        
        return ResponseEntity.ok(new MessageResponse("Class booked!"));
    }

    @PostMapping("/userClasses")
    public ResponseEntity<?> bookClass(@Valid @RequestBody UserRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        List<GymClass> userClasses = user.getGymClasses();
                        
        return ResponseEntity.ok(userClasses);
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