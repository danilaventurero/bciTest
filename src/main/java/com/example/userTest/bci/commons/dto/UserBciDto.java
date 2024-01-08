package com.example.userTest.bci.commons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserBciDto {
    public String name;
    public String email;
    public String password;
    public List<Phone> phones;
}
