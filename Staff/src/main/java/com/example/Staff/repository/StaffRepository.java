package com.example.Staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Staff.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{

}
