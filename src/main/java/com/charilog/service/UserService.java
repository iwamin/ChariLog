package com.charilog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charilog.domain.User;
import com.charilog.repository.UserJpaRepository;
import com.charilog.repository.UserRepository;

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

	public boolean authenticate(User user) {
		User found = userJpaRepository.findOne(user.getUserId());

		if (found == null) {
			return false;
		}

		if (found.getPassword().equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	public User create(User user) {
		return userJpaRepository.save(user);
	}
	
	public boolean delete(User user) {
		userJpaRepository.delete(user);
		return true;
	}

}
