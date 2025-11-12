package com.jtcindia.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtcindia.entity.StudentEntity;

public interface StudentRepository  extends JpaRepository<StudentEntity,Integer>{
	

}
