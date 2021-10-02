package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.model.User;

public interface UserService {

	List<User> getAllUsers();

	Optional<User> getUserById(Long id);
	
	User createUser(User user);

	void deleteUser(User user);

}
