package com.usman.contollers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT user FROM User user  WHERE user.username=(:username) AND user.password=(:password)")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
