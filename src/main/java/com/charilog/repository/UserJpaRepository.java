package com.charilog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charilog.domain.User;

public interface UserJpaRepository extends JpaRepository<User, String> {
}
