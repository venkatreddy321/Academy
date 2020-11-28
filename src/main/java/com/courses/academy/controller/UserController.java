package com.courses.academy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courses.academy.dto.LoginRequest;
import com.courses.academy.dto.ResponseDto;
import com.courses.academy.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<Optional<ResponseDto>> userLogin(@RequestBody LoginRequest user) {

		return new ResponseEntity<>(userService.loginUser(user.getUserId(), user.getPassword()), HttpStatus.OK);

	}
}
