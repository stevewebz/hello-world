package com.gymfitness.backend.controllers;

import java.util.List;

import com.gymfitness.backend.models.Attendance;
import com.gymfitness.backend.repositories.AttendanceRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping
    public List<Attendance> list(){
        return attendanceRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Attendance get(@PathVariable Long id){
        return attendanceRepository.getOne(id);
    }

    @PostMapping
    public Attendance create(@RequestBody final Attendance attendance){
        return attendanceRepository.saveAndFlush(attendance);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        attendanceRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public Attendance update(@PathVariable Long id, @RequestBody Attendance attendance){
        //put expects all, patch doesnt
        //todo: check all variables are here
        Attendance existingAttendance = attendanceRepository.getOne(id);
        BeanUtils.copyProperties(attendance, existingAttendance, "AttendanceId");
        return attendanceRepository.saveAndFlush(existingAttendance);
    }
}