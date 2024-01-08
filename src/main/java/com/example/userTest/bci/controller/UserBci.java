package com.example.userTest.bci.controller;

import com.example.userTest.bci.commons.dto.UserBciDto;
import com.example.userTest.bci.commons.resp.RespDto;
import com.example.userTest.bci.commons.resp.RespPhonesDto;
import com.example.userTest.bci.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/users")
public class UserBci {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody UserBciDto user
            ) throws JsonProcessingException {
        com.example.userTest.bci.model.UserBci u = new com.example.userTest.bci.model.UserBci();
        ObjectMapper mapper = new ObjectMapper();
    try{
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(user.getEmail());

        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
        } else {
            System.out.println("El email ingresado es inválido.");
            JSONObject resp = new JSONObject();
            resp.put("message","El email ingresado es invalido");
            return new ResponseEntity<>(resp.toString(),HttpStatus.BAD_REQUEST);
        }

        // El email a validar
        String email = "info@programacionextrema.com";
        u = userService.createUser(user);
    }catch(DataIntegrityViolationException e){
        JSONObject resp = new JSONObject();
        resp.put("message",e.getMessage());
        return new ResponseEntity<>(resp.toString(),HttpStatus.BAD_REQUEST);
    }

        return new ResponseEntity<>( mapper.writeValueAsString(entityToRespDto(u)),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) throws JsonProcessingException {
        com.example.userTest.bci.model.UserBci u = new com.example.userTest.bci.model.UserBci();
        ObjectMapper mapper = new ObjectMapper();
        try {

            u= userService.getById(id);
        } catch (EntityNotFoundException e) {
            JSONObject resp = new JSONObject();
            resp.put("message",e.getMessage());
            return new ResponseEntity<>(resp.toString(),HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>( mapper.writeValueAsString(entityToRespDto(u)),HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long id ,
            @RequestBody UserBciDto userBciDto
    ) throws JsonProcessingException {
        com.example.userTest.bci.model.UserBci u = new com.example.userTest.bci.model.UserBci();
        ObjectMapper mapper = new ObjectMapper();
        try {

            u= userService.updateUser(id,userBciDto);
        } catch (EntityNotFoundException e) {
            JSONObject resp = new JSONObject();
            resp.put("message",e.getMessage());
            return new ResponseEntity<>(resp.toString(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>( mapper.writeValueAsString(entityToRespDto(u)),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id){

        ObjectMapper mapper = new ObjectMapper();
        try {

            userService.removeUser(id);
        } catch (EntityNotFoundException e) {
            JSONObject resp = new JSONObject();
            resp.put("message",e.getMessage());
            return new ResponseEntity<>(resp.toString(),HttpStatus.NOT_FOUND);
        }

        JSONObject resp = new JSONObject();
        resp.put("message","User with id: "+id+" was removed");
        return new ResponseEntity<>(resp.toString() ,HttpStatus.OK);
    }

    public RespDto entityToRespDto(com.example.userTest.bci.model.UserBci u ){
        RespDto respPositive = new RespDto();
        respPositive.setId(u.getUserId().toString());
        respPositive.setName(u.getName());
        respPositive.setEmail(u.getEmail());
        respPositive.setPassword(u.getPassword());
        respPositive.setCreated(u.getCreated());
        respPositive.setModified(u.getModified());
        respPositive.setIsActive(u.isActive);
        respPositive.setToken(u.getToken());
        respPositive.setLastLogin(u.getLastLoggin());

        List<RespPhonesDto> respPhonesDtoList = new ArrayList<>();
        u.phones.forEach(userPhone -> {
            RespPhonesDto respPhonesDto = new RespPhonesDto();
            respPhonesDto.setCityCode(userPhone.getCityCode());
            respPhonesDto.setCountryCode(userPhone.countryCode);
            respPhonesDto.setNumber(userPhone.getNumber());
            respPhonesDtoList.add(respPhonesDto);
        });
        respPositive.setPhones(respPhonesDtoList);
        return respPositive;
    }
}
