package com.synergy.tms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy.tms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	

    // Filter by full name, email, or address (case-insensitive)
    Page<User> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrAddressContainingIgnoreCase(
        String fullName, String email, String address, Pageable pageable);
}

