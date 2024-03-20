package com.stanley.dodospring.repositories;

import com.stanley.dodospring.domain.UserRole;
import com.stanley.dodospring.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u where" +
            "(:username is null or u.username like %:username%)" +
            "and (:email is null or u.email like %:email%)" +
            "and (:password is null or u.password like %:password%)" +
            "and (:role is null or u.role = :role)")
    Iterable<UserEntity> filter(
            @Param("username") String username,
            @Param("email") String email,
            @Param("password") String password,
            @Param("role") UserRole role);

    Optional<UserEntity> findByEmail(String email);

}
