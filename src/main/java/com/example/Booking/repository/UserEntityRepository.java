package com.example.Booking.repository;

import com.example.Booking.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLogin(String username);

    @Query(value = "select role from user where login = ?1", nativeQuery = true)
    String getUserRole(String username);

    Optional<UserEntity> findByRole(String userRole);
}
