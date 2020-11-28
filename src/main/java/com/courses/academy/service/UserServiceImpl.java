package com.courses.academy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.courses.academy.dto.ResponseDto;
import com.courses.academy.entity.User;
import com.courses.academy.exception.InvalidUserException;
import com.courses.academy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	ResponseDto responseDto;

	@Override
	public Optional<ResponseDto> loginUser(String userId, String pwd) throws InvalidUserException {
		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new InvalidUserException("User does't exist");
		} 
		
		if (!user.get().getPassword().equals(pwd))
		{
			throw new InvalidUserException("Invalid credentials");
		}

		responseDto = new ResponseDto();
		responseDto.setMessage("Login Success");
		responseDto.setStatus(HttpStatus.OK.value());
		return Optional.of(responseDto);
	}

}
