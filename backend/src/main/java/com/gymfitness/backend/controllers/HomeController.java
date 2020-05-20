package com.gymfitness.backend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('MEMBER_BASIC') or hasAuthority('MEMBER_STANDARD') or hasAuthority('MEMBER_PREMIUM') or hasAuthority('INSTRUCTOR') or hasAuthority('ADMIN')")
	public String userAccess() {
		return "Member Content.";
	}

	@GetMapping("/instructor")
	@PreAuthorize("hasAuthority('INSTRUCTOR')")
	public String instructorAccess() {
		return "Instructor Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}