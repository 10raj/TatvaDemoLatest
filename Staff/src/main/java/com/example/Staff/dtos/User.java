package com.example.Staff.dtos;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private Long id;
	private String name;
	private String userName;
	private String password;
	private String email;
	private List<Role> roles;
}
