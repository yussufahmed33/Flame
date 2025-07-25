package com.flame.flame.repository;

import com.flame.flame.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
   UserModel findByUsername(String username);
}
