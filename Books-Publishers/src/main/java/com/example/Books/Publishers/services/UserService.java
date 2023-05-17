package com.example.Books.Publishers.services;

import java.util.List;

import com.example.Books.Publishers.entity.Role;
import com.example.Books.Publishers.entity.User;

public interface UserService {

	User save(User user);
	void setRoles(Long id, List<Role> roles);
	List<User> getUsers();
	User getByUserEmail(String email);
	
	
}
