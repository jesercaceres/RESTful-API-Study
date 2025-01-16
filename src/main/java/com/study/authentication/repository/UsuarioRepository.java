package com.study.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.authentication.model.User;

public interface  UsuarioRepository extends JpaRepository<User, Long> {
    
}
