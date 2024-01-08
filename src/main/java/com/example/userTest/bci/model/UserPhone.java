package com.example.userTest.bci.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserPhone {

    @Id
    @GeneratedValue
    private Long id;

    public String number;
    public String cityCode;
    public String countryCode;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "userId", nullable = false)
    public UserBci user;

}
