package com.example.userTest.bci.repositories;

import com.example.userTest.bci.model.UserBci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserBci, Long> {
}
