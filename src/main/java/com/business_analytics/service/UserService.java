package com.business_analytics.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business_analytics.model.dto.UserDTO;
import com.business_analytics.model.mapper.UserMapper;
import com.business_analytics.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public List<UserDTO> getAllUsers() {
    return userRepository.findAll()
        .stream()
        .map(userMapper::toDto)
        .toList();
  }

  public Optional<UserDTO> getUser(Long id) {
    return userRepository.findById(id)
        .map(userMapper::toDto);
  }

}
