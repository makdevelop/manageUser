package com.test.crud.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.test.crud.model.User;
import com.test.crud.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@Valid @RequestBody User u) {

		try {

			if (!u.getAdress().equalsIgnoreCase("france")) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			User _user = userService
					.addUser(new User(u.getNom(), u.getPrenom(), u.getEmail(), u.getAge(), u.getAdress()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> users = new ArrayList<User>();
			
			//List<User> users2Java8 = new ArrayList<User>();
			//userService.getAllUsers().forEach(users2Java8::add);

			users = userService.getAllUsers();
			

			System.out.println("sizeeee " + users.size());

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("findById/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") long id) {

		Optional<User> users = userService.findById(id);

		if (users.isPresent()) {
			return new ResponseEntity<>(users.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<HttpStatus> deleteUSer(@PathVariable("id") long id) {
		try {
			userService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteAllUser/{id}")
	public ResponseEntity<HttpStatus> deleteAllUser() {
		try {
			userService.deleteAllUser();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateUSer(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> users = userService.findById(id);

		if (users.isPresent()) {

			User _user = users.get();
			_user.setAdress(user.getAdress());
			_user.setAge(user.getAge());
			_user.setEmail(user.getEmail());
			_user.setNom(user.getNom());
			_user.setPrenom(user.getPrenom());

			return new ResponseEntity<>(userService.addUser(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/findUserByAdress")
	  public ResponseEntity<List<User>> findByName(@RequestParam String adress) {
	    try {
	      List<User> users = userService.findUserByAdress(adress);

	      if (users.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(users, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


}
