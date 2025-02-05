package com.study.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.authentication.model.User;

public interface  UsuarioRepository extends JpaRepository<User, Long> {
 
}
