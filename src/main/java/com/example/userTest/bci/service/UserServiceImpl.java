package com.example.userTest.bci.service;

import com.example.userTest.bci.commons.dto.UserBciDto;
import com.example.userTest.bci.model.UserBci;
import com.example.userTest.bci.model.UserPhone;
import com.example.userTest.bci.repositories.PhonesRepository;
import com.example.userTest.bci.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    PhonesRepository phonesRepository;
    public UserBci createUser(UserBciDto userBciDto) {
        try {


        UserBci user = dtoToEntity(userBciDto);

        user = userRepository.save(user);

        return user;

        } catch (DataIntegrityViolationException v){
            System.out.println(v.getCause());
            throw v;
        }catch (Exception e){
            System.out.println(e.getCause());
            return null;
        }
    }

    @Override
    public UserBci getById(Long id) {
        UserBci user = new UserBci();
        user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found - " + id));
        return user;
    }

    @Override
    public UserBci updateUser(Long id, UserBciDto requestBody) {

        UserBci user = new UserBci();
        user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found - " + id));
        user.setName(requestBody.getName());
        user.setEmail(requestBody.getEmail());
        user.setPassword(requestBody.getPassword());

        List<UserPhone> phones = user.getPhones();
        UserBci finalUser = user;
        requestBody.getPhones().forEach(phone -> {
            UserPhone up = new UserPhone();
            up.setNumber(phone.getNumber());
            up.setCountryCode(phone.getCountryCode());
            up.setCityCode(phone.getCityCode());
            up.setUser(finalUser);
            phones.add(up);
        });
        user.setPhones(phones);
        user.setModified(new Date());
        userRepository.save(user);
        return user;
    }

    @Override
    public void removeUser(Long id) {
        UserBci user = new UserBci();
        user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found - " + id));
        userRepository.delete(user);
    }

    public UserBci dtoToEntity(UserBciDto userBciDto){

        UserBci user = new UserBci();
        user.name = userBciDto.name;
        user.email = userBciDto.email;
        user.password = userBciDto.password;

        List<UserPhone> phones = new ArrayList<>();
        userBciDto.getPhones().forEach(p -> {
            UserPhone phoneAux = new UserPhone();
            phoneAux.setNumber(p.getNumber());
            phoneAux.setCityCode(p.getCityCode());
            phoneAux.setCountryCode(p.countryCode);
phoneAux.setUser(user);
            phones.add(phoneAux);
        });
        user.setPhones(phones);
        return user;
    }
}
