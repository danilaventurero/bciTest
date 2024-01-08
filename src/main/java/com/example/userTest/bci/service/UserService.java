package com.example.userTest.bci.service;

import com.example.userTest.bci.commons.dto.UserBciDto;
import com.example.userTest.bci.model.UserBci;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserBci createUser(UserBciDto userBciDto);
    public UserBci getById(Long id);
    public UserBci updateUser(Long id, UserBciDto user);
    public void removeUser(Long id);
}
