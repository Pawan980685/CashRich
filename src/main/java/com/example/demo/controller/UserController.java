package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.UserService;
import com.example.demo.user.User;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@Validated @RequestBody User user) {
		return ResponseEntity.ok(userService.saveUser(user));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		Optional<User> userOptional = userService.findByUsername(username);
		if (userOptional.isPresent()
				&& new BCryptPasswordEncoder().matches(password, userOptional.get().getPassword())) {
			return ResponseEntity.ok("Login successful");
		}
		return ResponseEntity.badRequest().body("Invalid username or password");
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		return ResponseEntity.ok(userService.updateUser(user));
	}
	
	/*
	 * @PutMapping("/{id}") public ResponseEntity<User> updateUser(@PathVariable
	 * Long id, @RequestBody User user) { User existingUser =
	 * userService.findById(id); if (existingUser != null) {
	 * existingUser.setUsername(user.getUsername());
	 * existingUser.setEmail(user.getEmail());
	 * existingUser.setFirstName(user.getFirstName());
	 * existingUser.setLastName(user.getLastName());
	 * existingUser.setMobile(user.getMobile()); User updatedUser =
	 * userService.saveUser(existingUser); return ResponseEntity.ok(updatedUser); }
	 * else { return ResponseEntity.status(404).body(null); } }
	 */
}
