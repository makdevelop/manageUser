package com.test.crud.service;

import java.util.List;
import java.util.Optional;

import com.test.crud.model.User;

public interface UserService {
	
	User addUser(User user);
	
	List<User> getAllUsers();

	void deleteById(long id);

	Optional<User> findById(long id);

	void deleteAllUser();

	List<User> findUserByAdress(String adress);

}
