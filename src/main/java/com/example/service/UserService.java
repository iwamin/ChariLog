package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserJpaRepository;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserJpaRepository userJpaRepository;

	@Autowired
	UserRepository userRepository;

	public List<User> findAll() {
		return userJpaRepository.findAll();
	}

	public boolean isExisting(User user) {
		return (userJpaRepository.findOne(user.getUserId()) != null);
	}

	public User create(User user) {
		return userJpaRepository.save(user);
	}
	
	public boolean delete(User user) {
		userJpaRepository.delete(user);
		return true;
	}

}
