package com.example.UserManagementService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.UserManagementService.entity.Role;
import com.example.UserManagementService.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


	Optional<User> findByEmail(String email);

	Optional<User> findByUserName(String userName);

	

	User findByUserNameAndPassword(String username, String password);

	Optional<User> findByUserNameAndEmail(String userName, String email);

	Optional<User> findByUserNameOrEmail(String userName, String email);
}
