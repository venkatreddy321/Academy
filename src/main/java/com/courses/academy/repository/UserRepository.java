package com.courses.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.academy.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
