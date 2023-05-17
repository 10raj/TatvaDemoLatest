package com.example.UserManagementService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.UserManagementService.dto.UserAuthenticationDto;
import com.example.UserManagementService.dto.UserRequestDto;
import com.example.UserManagementService.dto.UserValidationDto;
import com.example.UserManagementService.entity.Role;
import com.example.UserManagementService.entity.User;
import com.example.UserManagementService.repository.RoleRepository;
import com.example.UserManagementService.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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
	@Override
	public String userRegistration(UserRequestDto userRequestDto){
		User user = new User();
		List<Role> roles = new ArrayList<>();
		Optional<User> userDb= userRepository.findByUserNameOrEmail(userRequestDto.getUserName(),userRequestDto.getEmail());
		if(userDb.isPresent())
			return "User Already Exist";
		if(userRequestDto.getRoleCodes()!=null) {
			roles= userRequestDto.getRoleCodes().stream().map((roleCode)->
			roleRepository.findByRoleCode(roleCode)).collect(Collectors.toList());
		}
		user.setRoles(roles);
		BeanUtils.copyProperties(userRequestDto, user);
		userRepository.save(user);
		return "User Registred Successfully";
	}
	@Override
	public void userAuthentication(UserAuthenticationDto userAuthenticationDto) throws Exception {
	    User userDb = userRepository.findByUserNameAndPassword(
	    		userAuthenticationDto.getUsername(),userAuthenticationDto.getPassword());
	    if(userDb==null) {
	    	throw new Exception("User with given credential doesn't exist");
	    }
	}
	@Override
	public User getUser(String userName) {
		User userDb = userRepository.findByUserName(userName).get();
		return userDb;
	}
	@Override
	public void userValidation(UserValidationDto  userValidationDto) throws Exception {

		Optional<User> userDb = userRepository.findByUserName(
	    		userValidationDto.getUsername());
	    if(userDb.isEmpty()) {
	    	throw new Exception("User with given username doesn't exist");
	    }
	    else {
	    	List<Role> roleDb = userDb.get().getRoles();
	    	log.info("userValidation in User Management :{}",roleDb);
	    	if(!roleDb.toString().equals(userValidationDto.getRoles().toString())) {
	    		throw new Exception("User with given role doesn't exist");
	    	}
	    		
	    		
	    }
	    
	    
	    
	}
	
	
	
}
