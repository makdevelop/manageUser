package com.test.crud.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.assertj.core.api.Assertions.assertThat;
import com.test.crud.model.User;
import com.test.crud.model.Users;
import com.test.crud.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	 /*  Mockito.doReturn(true).when(User.class)userService.addUser();
	when(userService.addUser((User) any(User.class))).thenReturn(true);

	Answer<List<Integer>> answer = setupDummyListAnswer(77, 88, 99);
	boolean answ = userService.addUser(null);
    Mockito.when(dummyClass.dummyMethod()).thenAnswer(true);*/
//	when(userService.addUser((User) any(User.class))).thenReturn(true);
	
	@Test
	public void testCreateUser() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		User user = new User(1L, "jean", "michel", "jeanmichel@gmail.com", 25, "France");
		ResponseEntity<User> responseEntity = userController.createUser(user);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");

	}

	@Test
	public void testGetAllUsers() {
		
		// given
		User user1 = new User(1L, "jean", "michel", "jeanmichel@gmail.com", 25, "France");
		User user2 = new User(2L, "alain", "jacque", "alainjacque@gmail.com", 30, "France");
        Users users = new Users();
        users.setListUser(Arrays.asList(user1, user2));
 
        when(userService.getAllUsers()).thenReturn((List<User>) users);
 
        // when
        Users result = (Users) userService.getAllUsers();
 
        // then
        assertThat(result.getListUser().size()).isEqualTo(2);
         
        assertThat(result.getListUser().get(0).getNom())
                        .isEqualTo(user1.getNom());
         
        assertThat(result.getListUser().get(1).getNom())
                        .isEqualTo(user2.getNom());
		

	}

}
