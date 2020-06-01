package com.gymfitness.backend.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.gymfitness.backend.models.GymClass;
import com.gymfitness.backend.models.Location;
import com.gymfitness.backend.models.User;
import com.gymfitness.backend.models.Waitlist;
import com.gymfitness.backend.payload.request.BookClassRequest;
import com.gymfitness.backend.payload.request.ClassRequest;
import com.gymfitness.backend.payload.request.UserRequest;
import com.gymfitness.backend.payload.response.MessageResponse;
import com.gymfitness.backend.repositories.GymClassRepository;
import com.gymfitness.backend.repositories.LocationRepository;
import com.gymfitness.backend.repositories.UserRepository;
import com.gymfitness.backend.repositories.WaitlistRepository;

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

    @Autowired
    WaitlistRepository waitlistRepository;

    @Autowired
    LocationRepository locationRepository;

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

    @PostMapping("/create")
    public ResponseEntity<?> createClass(@Valid @RequestBody ClassRequest request){
        User user = userRepository.findByUserId(request.getUserId());

        GymClass gymClass = new GymClass();

        Location location = locationRepository.findByLocationId(request.getLocationId());

        gymClass.setClassName(request.getClassName());
        gymClass.setDateTime(request.getDateTime());
        gymClass.setMaxCapacity(request.getMaxCapacity());
        gymClass.setTotalEnrolled(0);
        gymClass.setLocation(location);
        gymClass.setInstructor(user);
        gymClassRepository.save(gymClass);
                        
        return ResponseEntity.ok(new MessageResponse("Class Created!"));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateClass(@Valid @RequestBody ClassRequest request){
        User user = userRepository.findByUserId(request.getUserId());

        GymClass gymClass = gymClassRepository.findById(request.getClassId())
                        .orElseThrow(() -> new UsernameNotFoundException("GymClass Not Found with Class ID: " + request.getClassId().toString()));

        Location location = locationRepository.findByLocationId(request.getLocationId());

        gymClass.setClassName(request.getClassName());
        gymClass.setDateTime(request.getDateTime());
        gymClass.setMaxCapacity(request.getMaxCapacity());
        gymClass.setLocation(location);
        gymClass.setInstructor(user);
        gymClassRepository.save(gymClass);
                        
        return ResponseEntity.ok(new MessageResponse("Class Edited!"));
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookClass(@Valid @RequestBody BookClassRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        GymClass gymClass = gymClassRepository.findById(request.getClassid())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        if(userRepository.existsByUserIdAndGymClassesClassId(user.getUserId(), gymClass.getClassId())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You have already enrolled for this class!"));
        }

        Integer totalEnrolled = gymClass.getTotalEnrolled();
        Integer newEnrolled = totalEnrolled + 1;
        gymClass.setTotalEnrolled(newEnrolled);
        gymClassRepository.save(gymClass);
        List<GymClass> existingGymList = user.getGymClasses();
        existingGymList.add(gymClass);

        user.setGymClasses(existingGymList);
        userRepository.save(user);
                        
        return ResponseEntity.ok(new MessageResponse("Class booked!"));
    }

    @PostMapping("/waitlist")
    public ResponseEntity<?> joinWaitlist(@Valid @RequestBody BookClassRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        GymClass gymClass = gymClassRepository.findById(request.getClassid())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        List<Waitlist> waitlistList = waitlistRepository.findAll();
        Integer pos = 1;
        if(waitlistList.size() > 0){
            for (Waitlist waitlist : waitlistList) {
                if(waitlist.getPosition() > pos){
                    pos = waitlist.getPosition();
                }
            }
        }

        if(waitlistRepository.existsByUserUserIdAndGymClassClassId(user.getUserId(), gymClass.getClassId())){
            return ResponseEntity
					.badRequest()
					.body(new MessageResponse("You are already on the waitlist for this class!"));
        }
        Waitlist waitlist = new Waitlist(pos, user, gymClass);
        waitlistRepository.save(waitlist);
        
        return ResponseEntity.ok(new MessageResponse("Waitlist joined!"));
    }

    @PostMapping("/cancelClass")
    public ResponseEntity<?> cancelClass(@Valid @RequestBody BookClassRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        GymClass gymClass = gymClassRepository.findById(request.getClassid())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        List<GymClass> existingGymList = user.getGymClasses();
        existingGymList.remove(gymClass);

        user.setGymClasses(existingGymList);
        userRepository.save(user);

        List<Waitlist> waitlistList = waitlistRepository.findByGymClassClassId(gymClass.getClassId());
        if(waitlistList.size() > 0){
            for (Waitlist waitlist : waitlistList) {
                if(waitlist.getPosition() == 1){
                    User waitlistUser = waitlist.getUser();
                    List<GymClass> wuExistingGymList = user.getGymClasses();
                    wuExistingGymList.add(gymClass);
                    waitlistUser.setGymClasses(wuExistingGymList);
                    userRepository.save(waitlistUser);

                    waitlistRepository.delete(waitlist);
                } else {
                    waitlist.setPosition(waitlist.getPosition() - 1);
                    waitlistRepository.save(waitlist);
                }
            }
        } else {
            Integer totalEnrolled = gymClass.getTotalEnrolled();
            Integer newEnrolled = totalEnrolled - 1;
            gymClass.setTotalEnrolled(newEnrolled);
            gymClassRepository.save(gymClass);
        }
                        
        return ResponseEntity.ok(new MessageResponse("Class cancelled!"));
    }

    @PostMapping("/cancelWaitlist")
    public ResponseEntity<?> cancelWaitlist(@Valid @RequestBody BookClassRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        GymClass gymClass = gymClassRepository.findById(request.getClassid())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        List<Waitlist> waitlistList = waitlistRepository.findByGymClassClassId(gymClass.getClassId());
        Waitlist wl = waitlistRepository.findByUserUserIdAndGymClassClassId(user.getUserId(), gymClass.getClassId());
        Integer currentPos = wl.getPosition();

        for (Waitlist waitlist : waitlistList) {
            if(waitlist.getPosition() > currentPos){
                waitlist.setPosition(waitlist.getPosition() - 1);
                waitlistRepository.save(waitlist);
            }
        }

        waitlistRepository.delete(wl);
                        
        return ResponseEntity.ok(new MessageResponse("Waitlist cancelled!"));
    }

    @PostMapping("/deleteClass")
    public ResponseEntity<?> deleteClass(@Valid @RequestBody BookClassRequest request){
        GymClass gymClass = gymClassRepository.findById(request.getClassid())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        gymClassRepository.delete(gymClass);
                        
        return ResponseEntity.ok(new MessageResponse("Class deleted!"));
    }

    @PostMapping("/userClasses")
    public ResponseEntity<?> userClasses(@Valid @RequestBody UserRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        List<GymClass> userClasses = user.getGymClasses();
                        
        return ResponseEntity.ok(userClasses);
    }

    @PostMapping("/instructorClasses")
    public ResponseEntity<?> instructorClasses(@Valid @RequestBody UserRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        List<GymClass> instructorClasses = gymClassRepository.findByInstructor(user);
                        
        return ResponseEntity.ok(instructorClasses);
    }

    @PostMapping("/waitlistClasses")
    public ResponseEntity<?> waitlistClasses(@Valid @RequestBody UserRequest request){
        List<GymClass> waitlistClasses = new ArrayList<>();
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + request.getEmail()));

        List<Waitlist> waitlistList = waitlistRepository.findByUserUserId(user.getUserId());
        for (Waitlist waitlist : waitlistList) {
            GymClass gymClass = gymClassRepository.findByWaitlistWaitlistId(waitlist.getWaitlistId());
            waitlistClasses.add(gymClass);
        }
                        
        return ResponseEntity.ok(waitlistClasses);
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