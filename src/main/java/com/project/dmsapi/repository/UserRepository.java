package com.project.dmsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.dmsapi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
