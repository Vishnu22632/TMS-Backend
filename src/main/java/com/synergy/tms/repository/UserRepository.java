package com.synergy.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy.tms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
