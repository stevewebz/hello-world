package com.gymfitness.backend.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.gymfitness.backend.models.EnumLevel;
import com.gymfitness.backend.models.User;
import com.gymfitness.backend.models.UserLevel;
import com.gymfitness.backend.payload.request.UserRequest;
import com.gymfitness.backend.repositories.UserLevelRepository;
import com.gymfitness.backend.repositories.UserRepository;

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
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLevelRepository userLevelRepository;

    @PostMapping("/edit")
	public ResponseEntity<?> editUser(@Valid @RequestBody UserRequest request) {
        User user = userRepository.findByUserId(request.getUserId());
        user.setFirstname(request.getFirstname());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        
        String strLevels = request.getLevel();
        Set<UserLevel> levels = new HashSet<>();

		if (strLevels == null) {
			UserLevel memberLevel = userLevelRepository.findByLevelName(EnumLevel.MEMBER_BASIC)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			levels.add(memberLevel);
		} else {
			switch (strLevels) {
			case "MEMBER_STANDARD":
				UserLevel standardLevel = userLevelRepository.findByLevelName(EnumLevel.MEMBER_STANDARD)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				levels.add(standardLevel);

				break;
			case "MEMBER_PREMIUM":
				UserLevel premiumLevel = userLevelRepository.findByLevelName(EnumLevel.MEMBER_PREMIUM)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				levels.add(premiumLevel);

				break;
			case "ADMIN":
				UserLevel adminLevel = userLevelRepository.findByLevelName(EnumLevel.ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				levels.add(adminLevel);

				break;
			case "INSTRUCTOR":
				UserLevel instructorLevel = userLevelRepository.findByLevelName(EnumLevel.INSTRUCTOR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				levels.add(instructorLevel);

				break;
			default:
				UserLevel memberLevel = userLevelRepository.findByLevelName(EnumLevel.MEMBER_BASIC)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				levels.add(memberLevel);
			}
		}

        user.setUserLevel(levels);
        userRepository.save(user);

		return ResponseEntity.ok(user);
	}

    @GetMapping
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id){
        return userRepository.getOne(id);
    }

    @PostMapping
    public User create(@RequestBody final User user){
        return userRepository.saveAndFlush(user);
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //check for fk constraints.. add way to delete fk also
        userRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user){
        //put expects all, patch doesnt
        //todo: check all variables are here
        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user, existingUser, "UserId");
        return userRepository.saveAndFlush(existingUser);
    }
}