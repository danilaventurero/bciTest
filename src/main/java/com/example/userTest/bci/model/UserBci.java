package com.example.userTest.bci.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class UserBci {
    @Id
    @GeneratedValue
    private Long userId;

    public String name;

    @Column(unique = true)
    public String email;
    public String password;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date created = new Date();

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date modified = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date lastLoggin = new Date();

    public UUID token = UUID.randomUUID();

    public boolean isActive = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<UserPhone> phones;

}
