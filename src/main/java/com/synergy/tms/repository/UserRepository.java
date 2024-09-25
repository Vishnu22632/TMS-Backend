package com.synergy.tms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy.tms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	// Use pageable to support pagination
    Page<User> findAll(Pageable pageable);
}

