package com.courses.academy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.courses.academy.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	/*
	 * @Query("select u from User u where u.userId =:userId and u.password =:password"
	 * ) User findByIdAndPwd(String userId,String password);
	 */
}
