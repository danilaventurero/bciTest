package com.example.userTest.bci.repositories;

import com.example.userTest.bci.model.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonesRepository extends JpaRepository<UserPhone, Long> {
}
