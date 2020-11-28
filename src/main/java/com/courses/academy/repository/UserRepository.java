package com.courses.academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.courses.academy.entity.User;

@Repository
public interface UserRepository extends JpaRepository<Optional<User>, String>{
	
	  @Query("select user from User u where u.userId =:userId and u.password =:password")
	  Optional<User> findByNameAndPwd(String userId,String password);

}
