package com.courses.academy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.courses.academy.dto.ResponseDto;
import com.courses.academy.exception.InvalidUserException;

@Service
public interface UserService{
	
    public Optional<ResponseDto> loginUser(String userId, String pwd) throws InvalidUserException;
}
