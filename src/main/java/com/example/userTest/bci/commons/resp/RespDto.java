package com.example.userTest.bci.commons.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RespDto {

    public String id;
    public String name;
    public String email;
    public String password;
    public List<RespPhonesDto> phones;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date created;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date modified;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date lastLogin;
    public UUID token;
    public Boolean isActive;
}
