package com.willis.trading.repository;

import com.willis.trading.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByEmail(String email);
}
