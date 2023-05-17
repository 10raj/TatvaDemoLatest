package com.example.Readers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Readers.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByRoleCode(Integer code);
	Role findByRoleName(String roleName);
}
