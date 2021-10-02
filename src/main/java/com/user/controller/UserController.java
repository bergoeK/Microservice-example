package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.user.model.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {


	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {

		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<User>  getUserDeatail(@PathVariable Long id) {

		return userService.getUserById(id)
				.map((user) -> new ResponseEntity<User>(user, HttpStatus.OK))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id :" + id));
		// or .map((user) -> new ResponseEntity<User>(user, HttpStatus.OK))
		// .orElseGet(() -> new ResponseEntity<User>(HttpStatus.NOT_FOUND));

	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User userContent, @PathVariable Long id) {
		
//		User user = userService.getUserById(id)
//				.orElseGet(() -> new User());

		User user = userService.getUserById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		user.setEmail(userContent.getEmail());
		user.setFirstName(userContent.getFirstName());
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
		
	}

	public ResponseEntity<User> deletUser(@PathVariable Long id) {

		User user = userService.getUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		userService.deleteUser(user);
		return ResponseEntity.ok().build();

	}

}
