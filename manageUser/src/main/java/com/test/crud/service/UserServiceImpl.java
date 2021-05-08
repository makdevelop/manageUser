package com.test.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.crud.model.User;
import com.test.crud.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		return userRepository.save(user);
		
	}

	@Override
	public List<User> getAllUsers() {
		System.out.println("service layer");
		return userRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findById(long id) {

		return userRepository.findById(id);
	}

	@Override
	public void deleteAllUser() {
		userRepository.deleteAll();
		
	}

	@Override
	public List<User> findUserByAdress(String adress) {
		return userRepository.findUserByAdress(adress);
	}

}
