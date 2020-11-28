package com.courses.academy.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.courses.academy.dto.ResponseDto;
import com.courses.academy.entity.User;
import com.courses.academy.exception.UserNotFoundException;
import com.courses.academy.repository.UserRepository;
import com.courses.academy.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	ResponseDto responseDto;

	@Override
	public Optional<ResponseDto> loginUser(String userId, String pwd) {
		User user = userRepository.findByNameAndPwd(userId, pwd);

		if (user!=null) {
			if (user.getEmailId().equals(userId) && user.getPassword().equals(pwd))

				responseDto = new ResponseDto();
			responseDto.setMessage("Login Success");
			responseDto.setStatus(HttpStatus.OK.value());
			return Optional.of(responseDto);
		} else {
			throw new UserNotFoundException("User Credentilas incorrect");
		}

	}

}
