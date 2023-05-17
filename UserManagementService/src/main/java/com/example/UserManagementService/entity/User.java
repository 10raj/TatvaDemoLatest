package com.example.UserManagementService.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	private String middleName;
	private String lastName;
	private String address1;
	private String address2;
	private Long zip;
	private String city;
	private String State;
	private String country;
	@Column(unique=true)
	private String userName;
	private String password;
	@Column(unique=true)
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
}
