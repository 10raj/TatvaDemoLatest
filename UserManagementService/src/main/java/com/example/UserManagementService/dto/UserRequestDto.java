package com.example.UserManagementService.dto;

import java.util.List;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	
	private String firstname;
	private String middleName;
	private String lastName;
	private String address1;
	private String address2;
	private Long zip;
	private String city;
	private String State;
	private String country;
	private String userName;
	private String password;
	private String email;
	private List<Integer> roleCodes;
}
