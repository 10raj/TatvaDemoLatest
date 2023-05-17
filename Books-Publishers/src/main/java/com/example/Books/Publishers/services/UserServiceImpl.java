package com.example.Books.Publishers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Books.Publishers.entity.Role;
import com.example.Books.Publishers.entity.User;
import com.example.Books.Publishers.repository.RoleRepository;
import com.example.Books.Publishers.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	@Override
	public void setRoles(Long id, List<Role> roles) {
		User user=userRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Invalid User Id : "+id));
		user.setRoles(roles);
	}
	@Override
	public List<User> getUsers() {
		List<User> users=userRepository.findAll();
		return users;
	}
	
	public User getByUserEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(()-> new IllegalArgumentException("Invalid User Email : "+email));
		return user;
	}
	
	
	
	
}
