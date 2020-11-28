package com.courses.academy.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.courses.academy.dto.ResponseDto;
import com.courses.academy.entity.User;
import com.courses.academy.service.UserService;

@RestController
public class UserController {

	//@Autowired
	private UserService userService;
	
	 public void setUserService(UserService userService) {
	      this.userService = userService;
	   }
	   // a getter method to return spellChecker
	   public UserService getUserService() {
	      return userService;
	   }

	@PostMapping("/login")
	public ResponseEntity<Optional<ResponseDto>> userLogin(@RequestBody User user) {

		return new ResponseEntity<>(userService.loginUser(user.getUserId(), user.getPassword()), HttpStatus.OK);

	}
}
